package main.java.com.narxoz.rpg.combat.facade;

import main.java.com.narxoz.rpg.combat.battle.*;
import main.java.com.narxoz.rpg.combat.decorator.AttackAction;

import java.util.Scanner;

public class DungeonFacade {

    private final PreparationService preparation = new PreparationService();
    private final BattleService battle = new BattleService();
    private final RewardService rewards = new RewardService();

    public void runAdventure(HeroCombatantAdapter hero, Scanner scanner) {
        System.out.println("\n  Starting gold: " + rewards.getGold() + " crowns");
        System.out.println("  Level: " + rewards.getLevel());

        while (true) {
            System.out.println("\n  [1] Take a contract");
            System.out.println("  [2] End adventure");
            System.out.print("  > ");

            String line = scanner.nextLine().trim();
            if (line.equals("2")) break;
            if (!line.equals("1")) { System.out.println("  Invalid choice."); continue; }

            Combatant enemy = preparation.pickEncounter(scanner);

            AttackAction blade = preparation.prepareSword(hero, rewards, scanner);

            EncounterResult result = battle.runBattle(hero, enemy, blade, scanner);

            boolean playerWon = result.getWinner().getName().equals(hero.getName());

            if (playerWon) {
                rewards.printRewards(result.getLoser(), hero.getHero());
            } else {
                System.out.println("\n  The Witcher has fallen. No rewards this time.");
            }

            BattleEngine.getInstance().reset(hero);
            hero = new HeroCombatantAdapter(hero.getHero());
        }

        rewards.printFinalSummary();
    }
}