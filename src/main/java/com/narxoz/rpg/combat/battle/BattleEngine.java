package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.ability.undead.GhostMode;
import main.java.com.narxoz.rpg.combat.ability.vampire.LifeDrain;
import main.java.com.narxoz.rpg.combat.ability.undead.BloodThirst;
import main.java.com.narxoz.rpg.combat.bridge.Skill;
import main.java.com.narxoz.rpg.combat.chain.*;
import main.java.com.narxoz.rpg.combat.command.*;
import main.java.com.narxoz.rpg.combat.observer.*;
import main.java.com.narxoz.rpg.enemy.Enemy;

import java.util.*;

public class BattleEngine {

    private static volatile BattleEngine instance;
    private Random random = new Random();
    private final List<String> combatLog = new ArrayList<>();
    private int specialUsesLeft = 3;

    private BattleEngine() {}

    public static BattleEngine getInstance() {
        if (instance == null) {
            synchronized (BattleEngine.class) {
                if (instance == null) instance = new BattleEngine();
            }
        }
        return instance;
    }

    public void setRandomSeed(long seed) { this.random = new Random(seed); }

    public void reset(HeroCombatantAdapter player) {
        combatLog.clear();
        specialUsesLeft = 3;
        player.setCurrentHealth(player.getMaxHealth());
    }

    public EncounterResult runEncounter(Combatant player, Combatant enemy, Scanner scanner) {
        combatLog.clear();
        specialUsesLeft = 3;
        int round = 0;

        // Chain of Responsibility: defense chain
        DodgeHandler dodge = new DodgeHandler(player.getAgilityValue());
        BlockHandler block = new BlockHandler(0.3);
        ArmorHandler armor = new ArmorHandler(player.getDefencePower());
        HpHandler hp = new HpHandler();
        dodge.setNext(block).setNext(armor).setNext(hp);

        // Command pattern: action queue
        ActionQueue queue = new ActionQueue();

        // Observer pattern: event system
        EventPublisher publisher = new EventPublisher();
        BattleLogger logger = new BattleLogger();
        AchievementObserver achievements = new AchievementObserver();
        HeroSupportObserver support = new HeroSupportObserver(player);
        BossStrategyObserver bossStrategy = new BossStrategyObserver();

        publisher.subscribe(logger);
        publisher.subscribe(achievements);
        publisher.subscribe(support);
        if (enemy.isBoss()) publisher.subscribe(bossStrategy);

        System.out.println("\n====================================");
        System.out.println("         ENCOUNTER BEGIN");
        System.out.println("====================================");
        System.out.println("  " + player.getName() + "  vs  " + enemy.getName());
        if (enemy.isBoss()) System.out.println("  ** THIS IS A BOSS FIGHT **");
        System.out.println("  Enemy weakness: " + WeaknessChart.getWeaknessSchool(enemy.getName()));
        System.out.println("====================================");

        boolean playerDefendedLastRound = false;
        boolean enemyDefendedLastRound = false;

        while (player.isAlive() && enemy.isAlive()) {
            round++;
            System.out.println("\n--- Round " + round + " ---");
            System.out.println("  " + player.getStatusBar());
            System.out.println("  " + enemy.getStatusBar());
            if (enemy.isBoss()) System.out.println("  Boss strategy: " + bossStrategy.getCurrentStrategy().getStrategyName());

            // player turn
            int actions = playerDefendedLastRound ? 2 : 1;
            playerDefendedLastRound = false;
            if (actions == 2) log(">> Defend bonus: 2 actions this turn! <<");

            for (int i = 0; i < actions && player.isAlive() && enemy.isAlive(); i++) {
                if (actions == 2) System.out.println("  >> Action " + (i + 1) + " of 2 <<");

                ActionCommand cmd = pickPlayerCommand(player, enemy, enemyDefendedLastRound, dodge, publisher, scanner);
                queue.enqueue(cmd);
                queue.executeAll();

                if (cmd instanceof DefendCommand) playerDefendedLastRound = true;

                // check boss phase and fire event
                if (enemy.isAlive() && enemy.isBoss()) {
                    checkBossPhase(enemy, publisher);
                }
            }

            if (!enemy.isAlive()) {
                publisher.fireEvent(new GameEvent(GameEventType.ENEMY_DEFEATED, enemy.getName(),
                        enemy.getName() + " has been slain!"));
                break;
            }

            // enemy turn: damage through defense chain, modified by strategy
            System.out.println("\n  -- " + enemy.getName() + "'s Turn --");
            enemyDefendedLastRound = doEnemyTurn(enemy, player, dodge, bossStrategy, publisher);

            // check hero low HP
            if (player.isAlive()) {
                double hpPercent = (double) player.getCurrentHealth() / player.getMaxHealth();
                if (hpPercent <= 0.25) {
                    publisher.fireEvent(new GameEvent(GameEventType.HERO_LOW_HP, player.getName(),
                            player.getName() + " is critically wounded!", (int)(hpPercent * 100)));
                }
            }

            if (!player.isAlive()) {
                publisher.fireEvent(new GameEvent(GameEventType.HERO_DEFEATED, player.getName(),
                        player.getName() + " has fallen!"));
                break;
            }
            System.out.println();
        }

        Combatant winner = player.isAlive() ? player : enemy;
        Combatant loser = player.isAlive() ? enemy : player;

        System.out.println();
        achievements.printAchievements();

        EncounterResult result = new EncounterResult(winner, loser, round, new ArrayList<>(combatLog));
        result.printSummary();
        return result;
    }

    private ActionCommand pickPlayerCommand(Combatant player, Combatant enemy,
                                            boolean enemyDefending, DodgeHandler dodge,
                                            EventPublisher publisher, Scanner scanner) {
        System.out.println("\n  -- " + player.getName() + "'s Turn --");
        boolean hasPot = (player instanceof HeroCombatantAdapter)
                && ((HeroCombatantAdapter) player).hasPotionAvailable();

        while (true) {
            System.out.println("  [1] Sword Attack");
            System.out.println("  [2] Cast Sign");
            System.out.println("  [3] Defend (dodge +15%, get 2 actions next turn)");
            if (hasPot) System.out.println("  [4] Use Potion");
            if (specialUsesLeft > 0) System.out.println("  [5] Special (" + specialUsesLeft + "/3)");
            System.out.print("  > ");

            int choice = readInt(scanner, 1, 5);

            switch (choice) {
                case 1:
                    int dmg = rollDamage(player.getAttackPower());
                    if (enemyDefending) dmg = (int)(dmg * 0.4);
                    boolean crit = random.nextInt(100) < 15;
                    if (crit) dmg = (int)(dmg * 1.5);
                    dmg = checkGhostMode(dmg, enemy);
                    if (crit) publisher.fireEvent(new GameEvent(GameEventType.CRITICAL_HIT, player.getName(),
                            player.getName() + " lands a critical hit!", dmg));
                    publisher.fireEvent(new GameEvent(GameEventType.ATTACK_LANDED, player.getName(),
                            player.getName() + " attacks for " + dmg, dmg));
                    return new AttackCommand(player, enemy, dmg);

                case 2:
                    List<Ability> abilities = player.getAbilities();
                    if (abilities == null || abilities.isEmpty()) {
                        System.out.println("  No signs available!");
                        continue;
                    }
                    for (int i = 0; i < abilities.size(); i++) {
                        Ability a = abilities.get(i);
                        String tag = WeaknessChart.isWeak(enemy.getName(), a) ? " [WEAK!]" : "";
                        String aoe = (a instanceof Skill && ((Skill) a).isArea()) ? " [AOE]" : "";
                        System.out.println("  [" + (i+1) + "] " + a.getName() + " (Dmg:" + a.getDamage() + ")" + tag + aoe);
                    }
                    System.out.print("  > ");
                    Ability chosen = abilities.get(readInt(scanner, 1, abilities.size()) - 1);
                    int signDmg = chosen.getDamage() + (player.getMagicPower() / 4);
                    if (enemyDefending) signDmg = (int)(signDmg * 0.4);
                    if (WeaknessChart.isWeak(enemy.getName(), chosen)) signDmg = (int)(signDmg * 1.5);
                    signDmg = checkGhostMode(signDmg, enemy);
                    publisher.fireEvent(new GameEvent(GameEventType.ATTACK_LANDED, player.getName(),
                            player.getName() + " casts " + chosen.getName() + " for " + signDmg, signDmg));

                    final int finalSignDmg = signDmg;
                    final Ability finalChosen = chosen;
                    if (chosen instanceof Skill && ((Skill) chosen).isArea() && enemy instanceof CombatGroup) {
                        return new AttackCommand(player, enemy, signDmg) {
                            @Override
                            public void execute() {
                                ((CombatGroup) enemy).takeAreaDamage(finalSignDmg);
                                System.out.println("  " + player.getName() + " casts " + finalChosen.getName() + " hitting ALL enemies for " + finalSignDmg + " each!");
                            }
                            @Override
                            public String getDescription() { return "AOE: " + finalChosen.getName() + " (" + finalSignDmg + " each)"; }
                        };
                    }
                    return new AttackCommand(player, enemy, signDmg);

                case 3:
                    return new DefendCommand(player.getName(), dodge, 15);

                case 4:
                    if (!hasPot) { System.out.println("  No potions left!"); continue; }
                    int healAmt = (int)(player.getMaxHealth() * 0.35);
                    publisher.fireEvent(new GameEvent(GameEventType.HEAL_USED, player.getName(),
                            player.getName() + " uses a potion", healAmt));
                    return new HealCommand(player, healAmt);

                case 5:
                    if (specialUsesLeft <= 0) { System.out.println("  No special uses left!"); continue; }
                    specialUsesLeft--;
                    int specDmg = 200 + (int)(player.getAttackPower() * 0.5 + player.getMagicPower() * 0.3);
                    if (enemyDefending) specDmg = (int)(specDmg * 0.4);
                    specDmg = checkGhostMode(specDmg, enemy);
                    publisher.fireEvent(new GameEvent(GameEventType.ATTACK_LANDED, player.getName(),
                            player.getName() + " unleashes special for " + specDmg, specDmg));
                    return new AttackCommand(player, enemy, specDmg);
            }
        }
    }

    private boolean doEnemyTurn(Combatant enemy, Combatant player,
                                DefenseHandler defenseChain, BossStrategyObserver bossStrategy,
                                EventPublisher publisher) {
        CombatAction action = pickEnemyAction(enemy);

        if (action == CombatAction.DEFEND) {
            log(enemy.getName() + " fortifies its defences!");
            return true;
        }

        int dmg;

        if (action == CombatAction.SIGN_CAST) {
            Ability ability = enemy.getAbilities().get(random.nextInt(enemy.getAbilities().size()));
            dmg = ability.getDamage() + (enemy.getMagicPower() / 4);
            dmg = checkBloodThirst(dmg, enemy);
            // Strategy: boss modifies damage
            if (enemy.isBoss()) dmg = bossStrategy.getCurrentStrategy().calculateDamage(dmg);
            log(enemy.getName() + " casts " + ability.getName() + "!");
            publisher.fireEvent(new GameEvent(GameEventType.ATTACK_LANDED, enemy.getName(),
                    enemy.getName() + " casts " + ability.getName() + " for " + dmg, dmg));
            defenseChain.handle(dmg, player);

            if (ability instanceof LifeDrain && player.isAlive()) {
                int heal = (int)(dmg * LifeDrain.HEAL_PERCENT);
                enemy.heal(heal);
                log(enemy.getName() + " drains " + heal + " HP!");
            }
        } else {
            dmg = rollDamage(enemy.getAttackPower());
            boolean crit = random.nextInt(100) < 10;
            if (crit) { dmg = (int)(dmg * 1.5); log("CRITICAL HIT from " + enemy.getName() + "!"); }
            dmg = checkBloodThirst(dmg, enemy);
            if (enemy.isBoss()) dmg = bossStrategy.getCurrentStrategy().calculateDamage(dmg);
            log(enemy.getName() + " attacks!");
            publisher.fireEvent(new GameEvent(GameEventType.ATTACK_LANDED, enemy.getName(),
                    enemy.getName() + " attacks for " + dmg, dmg));
            defenseChain.handle(dmg, player);
        }
        return false;
    }

    private void checkBossPhase(Combatant enemy, EventPublisher publisher) {
        if (!enemy.checkPhaseTransition() || !(enemy instanceof BossCombatantAdapter)) return;
        BossCombatantAdapter boss = (BossCombatantAdapter) enemy;
        String msg = boss.consumePhaseTransitionMessage();
        System.out.println("\n  !! BOSS PHASE TRANSITION !!");
        if (msg != null) System.out.println("  " + msg);
        System.out.println("  Phase " + (boss.getCurrentPhaseIndex() + 1) + "/" + boss.getTotalPhases() + ": " + boss.getPhaseName());
        System.out.println();

        int hpPercent = (int)(100.0 * enemy.getCurrentHealth() / enemy.getMaxHealth());
        publisher.fireEvent(new GameEvent(GameEventType.BOSS_PHASE_CHANGE, enemy.getName(),
                enemy.getName() + " enters new phase!", hpPercent));
    }

    private CombatAction pickEnemyAction(Combatant enemy) {
        boolean hasAbilities = enemy.getAbilities() != null && !enemy.getAbilities().isEmpty();
        String ai = getAIType(enemy);
        int roll = random.nextInt(100);

        if (ai.contains("aggressive")) {
            if (hasAbilities) return roll < 40 ? CombatAction.SWORD_ATTACK : roll < 75 ? CombatAction.SIGN_CAST : CombatAction.DEFEND;
            return roll < 80 ? CombatAction.SWORD_ATTACK : CombatAction.DEFEND;
        }
        if (ai.contains("tactical")) {
            if (hasAbilities) return roll < 15 ? CombatAction.SWORD_ATTACK : roll < 50 ? CombatAction.SIGN_CAST : CombatAction.DEFEND;
            return roll < 35 ? CombatAction.SWORD_ATTACK : CombatAction.DEFEND;
        }
        if (ai.contains("defensive")) {
            if (hasAbilities) return roll < 15 ? CombatAction.SWORD_ATTACK : roll < 30 ? CombatAction.SIGN_CAST : CombatAction.DEFEND;
            return roll < 25 ? CombatAction.SWORD_ATTACK : CombatAction.DEFEND;
        }
        return CombatAction.SWORD_ATTACK;
    }

    private int checkGhostMode(int dmg, Combatant target) {
        if (!hasAbility(target, GhostMode.class)) return dmg;
        int reduced = (int)(dmg * (1.0 - GhostMode.DAMAGE_REDUCTION));
        log("Ghost Mode! (" + dmg + " -> " + reduced + ")");
        return reduced;
    }

    private int checkBloodThirst(int dmg, Combatant attacker) {
        if (!hasAbility(attacker, BloodThirst.class)) return dmg;
        if ((double) attacker.getCurrentHealth() / attacker.getMaxHealth() > BloodThirst.HP_THRESHOLD) return dmg;
        int boosted = (int)(dmg * (1.0 + BloodThirst.DAMAGE_BOOST));
        log("BLOOD FRENZY! (" + dmg + " -> " + boosted + ")");
        return boosted;
    }

    private boolean hasAbility(Combatant c, Class<? extends Ability> type) {
        if (c.getAbilities() == null) return false;
        for (Ability a : c.getAbilities()) if (type.isInstance(a)) return true;
        return false;
    }

    private String getAIType(Combatant c) {
        if (c instanceof CombatGroup) {
            List<Combatant> alive = ((CombatGroup) c).getAliveMembers();
            if (!alive.isEmpty()) return getAIType(alive.get(0));
            return "aggressive";
        }
        Enemy e = null;
        if (c instanceof EnemyCombatantAdapter) e = ((EnemyCombatantAdapter) c).getEnemy();
        else if (c instanceof BossCombatantAdapter) e = ((BossCombatantAdapter) c).getEnemy();
        return e.getAIBehavior().getBehaviorType().toLowerCase();
    }

    private int rollDamage(int base) {
        int v = Math.max(1, (int)(base * 0.15));
        return base + random.nextInt(v * 2 + 1) - v;
    }

    private void log(String msg) {
        combatLog.add(msg);
        System.out.println("  " + msg);
    }

    private int readInt(Scanner sc, int min, int max) {
        while (true) {
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val >= min && val <= max) return val;
            } catch (NumberFormatException ignored) {}
            System.out.print("  Enter " + min + "-" + max + ": ");
        }
    }
}