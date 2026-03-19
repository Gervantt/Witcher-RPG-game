package main.java.com.narxoz.rpg.combat.facade;

import main.java.com.narxoz.rpg.combat.battle.*;
import main.java.com.narxoz.rpg.combat.decorator.*;

import java.util.Scanner;

public class PreparationService {

    public Combatant pickEncounter(Scanner scanner) {
        System.out.println("====================================");
        System.out.println("  CONTRACT BOARD - Pick a Contract");
        System.out.println("====================================");
        System.out.println("  [1] Nekker Gang         - Pack of Nekkers");
        System.out.println("  [2] Katakan             - Vampire hunt");
        System.out.println("  [3] Vampire Nest        - Group of vampires");
        System.out.println("  [4] Drowner Pack        - Swamp creatures");
        System.out.println("  [5] Graveyard Haunt     - Wraiths in a cemetery");
        System.out.println("  [6] Imlerith            - Wild Hunt General");
        System.out.println("  [7] Detlaff             - Higher Vampire");
        System.out.println("  [8] Eredin              - King of the Wild Hunt");
        System.out.print("  > ");

        int choice = readInt(scanner, 1, 8);

        switch (choice) {
            case 1: return new CombatGroup("Nekker Gang", EnemyEncounterFactory.createNekkerGang().toArray(new Combatant[0]));
            case 2: return EnemyEncounterFactory.createKatakan();
            case 3: return new CombatGroup("Vampire Nest", EnemyEncounterFactory.createVampireNest().toArray(new Combatant[0]));
            case 4: return new CombatGroup("Drowner Pack", EnemyEncounterFactory.createDrownerPack().toArray(new Combatant[0]));
            case 5: return new CombatGroup("Graveyard Haunt", EnemyEncounterFactory.createGraveyardHaunt().toArray(new Combatant[0]));
            case 6: return BossEncounterFactory.createImlerith();
            case 7: return BossEncounterFactory.createDetlaff();
            case 8: return BossEncounterFactory.createEredin();
            default: return EnemyEncounterFactory.createGhoul();
        }
    }

    public AttackAction prepareSword(HeroCombatantAdapter hero, RewardService wallet, Scanner scanner) {
        int baseAtk = hero.getAttackPower();

        System.out.println("\n====================================");
        System.out.println("  CHOOSE ATTACK STYLE");
        System.out.println("====================================");
        System.out.println("  [1] Fast Attack     (" + new BasicAttack(baseAtk).getDamage() + " dmg) - no side effects");
        System.out.println("  [2] Heavy Attack    (" + new HeavyAttack(baseAtk).getDamage() + " dmg) - costs 100 HP");
        System.out.println("  [3] Crossbow Shot   (" + new CrossbowShot(baseAtk).getDamage() + " dmg) - restores 50 HP");
        System.out.print("  > ");

        int style = readInt(scanner, 1, 3);
        AttackAction attack;

        switch (style) {
            case 2:
                attack = new HeavyAttack(baseAtk);
                hero.takeDamage(100);
                System.out.println("  The heavy stance strains your body! (-100 HP)");
                break;
            case 3:
                attack = new CrossbowShot(baseAtk);
                hero.heal(50);
                System.out.println("  Keeping distance lets you catch your breath! (+50 HP)");
                break;
            default:
                attack = new BasicAttack(baseAtk);
                break;
        }

        System.out.println("\n====================================");
        System.out.println("  APPLY ENHANCEMENT");
        System.out.println("====================================");
        System.out.println("  Gold: " + wallet.getGold() + " crowns");
        System.out.println("  [1] No enhancement       - free");
        System.out.println("  [2] Specter Oil          - 150 crowns (+20 flat damage)");
        System.out.println("  [3] Svarog Runestone     - 200 crowns (+30% fire damage)");
        System.out.println("  [4] Thunderbolt Potion   - 175 crowns (+40% damage)");
        System.out.print("  > ");

        int enhancement = readInt(scanner, 1, 4);

        switch (enhancement) {
            case 2:
                if (wallet.getGold() >= 150) {
                    wallet.spendGold(150);
                    attack = new SpecterOilDecorator(attack);
                } else {
                    System.out.println("  Not enough gold! Fighting without enhancement.");
                }
                break;
            case 3:
                if (wallet.getGold() >= 200) {
                    wallet.spendGold(200);
                    attack = new SvarogRunestoneDecorator(attack);
                } else {
                    System.out.println("  Not enough gold! Fighting without enhancement.");
                }
                break;
            case 4:
                if (wallet.getGold() >= 175) {
                    wallet.spendGold(175);
                    attack = new ThunderboltDecorator(attack);
                } else {
                    System.out.println("  Not enough gold! Fighting without enhancement.");
                }
                break;
        }

        System.out.println("  Blade ready: " + attack.getDescription() + " (" + attack.getDamage() + " dmg)");
        System.out.println("  Gold remaining: " + wallet.getGold() + " crowns");
        System.out.println("  HP: " + hero.getCurrentHealth() + "/" + hero.getMaxHealth());
        return attack;
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