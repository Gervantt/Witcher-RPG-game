package main.java.com.narxoz.rpg.combat.chain;

import main.java.com.narxoz.rpg.combat.battle.Combatant;

public class ArmorHandler extends DefenseHandler {

    private int armorValue;

    public ArmorHandler(int armorValue) {
        this.armorValue = armorValue;
    }

    @Override
    public void handle(int incomingDamage, Combatant target) {
        int absorbed = Math.min(armorValue, incomingDamage);
        int remaining = Math.max(0, incomingDamage - armorValue);
        if (absorbed > 0) System.out.println("  Armor absorbs " + absorbed + " damage!");
        passToNext(remaining, target);
    }
}