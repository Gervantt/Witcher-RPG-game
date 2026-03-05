package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.ability.undead.GhostMode;
import main.java.com.narxoz.rpg.combat.ability.vampire.LifeDrain;
import main.java.com.narxoz.rpg.combat.ability.undead.BloodThirst;
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
        player.setAttackPower(player.getHero().getStrength());
        player.setAgilityValue(player.getHero().getAgility());
        player.setMagicPower(player.getHero().getMagic());
    }

    public EncounterResult runEncounter(Combatant player, Combatant enemy, Scanner scanner) {
        combatLog.clear();
        specialUsesLeft = 3;
        int round = 0;

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

            boolean playerDefending = false;
            if (playerDefendedLastRound) log(">> Defend bonus: 2 actions this turn! <<");

            playerDefending = doPlayerTurn(player, enemy, enemyDefendedLastRound, scanner);
            checkBossPhase(enemy);

            if (playerDefendedLastRound && enemy.isAlive()) {
                log(">> Action 2 of 2 <<");
                playerDefending = doPlayerTurn(player, enemy, enemyDefendedLastRound, scanner);
                checkBossPhase(enemy);
            }
            playerDefendedLastRound = playerDefending;

            if (!enemy.isAlive()) break;

            boolean enemyDefending = false;
            System.out.println("\n  -- " + enemy.getName() + "'s Turn --");
            enemyDefending = doEnemyTurn(enemy, player, playerDefending);

            if (enemyDefendedLastRound && player.isAlive()) {
                System.out.println("\n  -- " + enemy.getName() + "'s Bonus Turn --");
                enemyDefending = doEnemyTurn(enemy, player, playerDefending);
            }
            enemyDefendedLastRound = enemyDefending;

            if (!player.isAlive()) break;
            System.out.println();
        }

        Combatant winner = player.isAlive() ? player : enemy;
        Combatant loser = player.isAlive() ? enemy : player;
        EncounterResult result = new EncounterResult(winner, loser, round, new ArrayList<>(combatLog));
        result.printSummary();
        return result;
    }

    private boolean doPlayerTurn(Combatant player, Combatant enemy, boolean enemyDefending, Scanner scanner) {
        System.out.println("\n  -- " + player.getName() + "'s Turn --");

        boolean hasPot = (player instanceof HeroCombatantAdapter)
                && ((HeroCombatantAdapter) player).hasPotionAvailable();

        int choice = 0;
        while (true) {
            System.out.println("  [1] Sword Attack");
            System.out.println("  [2] Cast Sign");
            System.out.println("  [3] Defend (block 60%, get 2 actions next turn)");
            if (hasPot) System.out.println("  [4] Use Potion");
            System.out.println("  [5] Special (" + specialUsesLeft + "/3 left)");
            System.out.print("  > ");
            choice = readInt(scanner, 1, 5);

            if (choice == 4 && !hasPot) { System.out.println("  No potions left!"); continue; }
            if (choice == 5 && specialUsesLeft <= 0) { System.out.println("  No special uses left!"); continue; }
            break;
        }

        switch (choice) {
            case 1: // sword attack
                int dmg = rollDamage(player.getAttackPower());
                if (enemyDefending) { dmg = (int)(dmg * 0.4); log(enemy.getName() + " blocks! (-60%)"); }
                if (random.nextInt(100) < 15) { dmg = (int)(dmg * 1.5); log("CRITICAL HIT!"); }
                dmg = checkGhostMode(dmg, enemy);
                enemy.takeDamage(dmg);
                log(player.getName() + " strikes for " + dmg + " damage!");
                break;

            case 2: // sign cast
                List<Ability> abilities = player.getAbilities();
                for (int i = 0; i < abilities.size(); i++) {
                    Ability a = abilities.get(i);
                    String tag = WeaknessChart.isWeak(enemy.getName(), a) ? " [WEAK!]" : "";
                    System.out.println("  [" + (i+1) + "] " + a.getName() + " (Dmg:" + a.getDamage() + ")" + tag);
                }
                System.out.print("  > ");
                Ability chosen = abilities.get(readInt(scanner, 1, abilities.size()) - 1);
                int signDmg = chosen.getDamage() + (player.getMagicPower() / 4);
                if (enemyDefending) { signDmg = (int)(signDmg * 0.4); log(enemy.getName() + " braces! (-60%)"); }
                if (WeaknessChart.isWeak(enemy.getName(), chosen)) {
                    signDmg = (int)(signDmg * 1.5);
                    log("WEAKNESS EXPLOITED! x1.5 damage!");
                }
                signDmg = checkGhostMode(signDmg, enemy);
                enemy.takeDamage(signDmg);
                log(player.getName() + " casts " + chosen.getName() + " for " + signDmg + " damage!");
                break;

            case 3: // defend
                log(player.getName() + " raises guard!");
                return true;

            case 4: // potion
                HeroCombatantAdapter hero = (HeroCombatantAdapter) player;
                int heal = (int)(player.getMaxHealth() * 0.35);
                player.heal(heal);
                hero.usePotionCharge();
                log(player.getName() + " drinks Swallow, restoring " + heal + " HP!");
                break;

            case 5: // special
                specialUsesLeft--;
                if (player instanceof HeroCombatantAdapter) {
                    ((HeroCombatantAdapter) player).getHero().useSpecialAbility();
                }
                int specDmg = 200 + (int)(player.getAttackPower() * 0.5 + player.getMagicPower() * 0.3);
                if (enemyDefending) specDmg = (int)(specDmg * 0.4);
                specDmg = checkGhostMode(specDmg, enemy);
                enemy.takeDamage(specDmg);
                log(player.getName() + "'s special deals " + specDmg + " damage! (" + specialUsesLeft + "/3 left)");
                break;
        }
        return false;
    }

    private boolean doEnemyTurn(Combatant enemy, Combatant player, boolean playerDefending) {
        CombatAction action = pickEnemyAction(enemy);

        if (action == CombatAction.DEFEND) {
            log(enemy.getName() + " fortifies its defences!");
            return true;
        }

        int dmg;
        String label;

        if (action == CombatAction.SIGN_CAST) {
            Ability ability = enemy.getAbilities().get(random.nextInt(enemy.getAbilities().size()));
            dmg = ability.getDamage() + (enemy.getMagicPower() / 4);
            label = enemy.getName() + " casts " + ability.getName();

            if (playerDefending) { dmg = (int)(dmg * 0.4); log(player.getName() + " blocks! (-60%)"); }
            dmg = checkBloodThirst(dmg, enemy);
            player.takeDamage(dmg);
            log(label + " for " + dmg + " damage!");

            if (ability instanceof LifeDrain) {
                int heal = (int)(dmg * LifeDrain.HEAL_PERCENT);
                enemy.heal(heal);
                log(enemy.getName() + " drains " + heal + " HP!");
            }
        } else {
            dmg = rollDamage(enemy.getAttackPower());
            if (playerDefending) { dmg = (int)(dmg * 0.4); log(player.getName() + " blocks! (-60%)"); }
            if (random.nextInt(100) < 10) { dmg = (int)(dmg * 1.5); log("CRITICAL HIT from " + enemy.getName() + "!"); }
            dmg = checkBloodThirst(dmg, enemy);
            player.takeDamage(dmg);
            log(enemy.getName() + " attacks for " + dmg + " damage!");
        }
        return false;
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

    private void checkBossPhase(Combatant enemy) {
        if (!enemy.checkPhaseTransition() || !(enemy instanceof BossCombatantAdapter)) return;
        BossCombatantAdapter boss = (BossCombatantAdapter) enemy;
        String msg = boss.consumePhaseTransitionMessage();
        System.out.println("\n  !! BOSS PHASE TRANSITION !!");
        if (msg != null) System.out.println("  " + msg);
        System.out.println("  Phase " + (boss.getCurrentPhaseIndex() + 1) + "/" + boss.getTotalPhases() + ": " + boss.getPhaseName());
        System.out.println();
    }

    private String getAIType(Combatant c) {
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