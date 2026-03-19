package main.java.com.narxoz.rpg.combat.facade;

import main.java.com.narxoz.rpg.combat.battle.*;
import main.java.com.narxoz.rpg.combat.decorator.AttackAction;

import java.util.Scanner;

public class BattleService {

    public EncounterResult runBattle(Combatant player, Combatant enemy, AttackAction attack, Scanner scanner) {
        BattleEngine engine = BattleEngine.getInstance();
        engine.setRandomSeed(System.currentTimeMillis());

        System.out.println("\n  Blade prepared: " + attack.getDescription());
        System.out.println("  Decorated damage: " + attack.getDamage());
        System.out.println();

        return engine.runEncounter(player, enemy, scanner);
    }
}