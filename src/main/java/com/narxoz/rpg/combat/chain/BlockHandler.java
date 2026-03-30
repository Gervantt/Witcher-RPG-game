package main.java.com.narxoz.rpg.combat.chain;

import main.java.com.narxoz.rpg.combat.battle.Combatant;

public class BlockHandler extends DefenseHandler {

    private double blockPercent;

    public BlockHandler(double blockPercent) {
        this.blockPercent = blockPercent;
    }

    @Override
    public void handle(int incomingDamage, Combatant target) {
        int blocked = (int)(incomingDamage * blockPercent);
        int remaining = incomingDamage - blocked;
        if (blocked > 0) System.out.println("  Blocked " + blocked + " damage! (" + (int)(blockPercent * 100) + "%)");
        passToNext(remaining, target);
    }
}