package main.java.com.narxoz.rpg.combat.chain;

import main.java.com.narxoz.rpg.combat.battle.Combatant;

public class HpHandler extends DefenseHandler {

    @Override
    public void handle(int incomingDamage, Combatant target) {
        target.takeDamage(incomingDamage);
        System.out.println("  " + target.getName() + " takes " + incomingDamage + " HP damage!");
    }

}