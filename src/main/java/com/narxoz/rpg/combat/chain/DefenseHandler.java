package main.java.com.narxoz.rpg.combat.chain;

import main.java.com.narxoz.rpg.combat.battle.Combatant;

public abstract class DefenseHandler {

    private DefenseHandler next;

    public DefenseHandler setNext(DefenseHandler next) {
        this.next = next;
        return next;
    }

    protected void passToNext(int damage, Combatant target) {
        if (next != null) next.handle(damage, target);
    }

    public abstract void handle(int incomingDamage, Combatant target);
}