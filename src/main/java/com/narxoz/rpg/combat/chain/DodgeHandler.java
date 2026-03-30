package main.java.com.narxoz.rpg.combat.chain;

import main.java.com.narxoz.rpg.combat.battle.Combatant;

import java.util.Random;

public class DodgeHandler extends DefenseHandler {

    private int dodgeChance;
    private Random random = new Random();

    public DodgeHandler(int dodgeChance) {
        this.dodgeChance = Math.min(80, dodgeChance);
    }

    public void modifyDodgeChance(int amount) {
        this.dodgeChance = Math.min(80, Math.max(0, dodgeChance + amount));
    }

    public int getDodgeChance() { return dodgeChance; }

    @Override
    public void handle(int incomingDamage, Combatant target) {
        if (random.nextInt(100) < dodgeChance) {
            System.out.println("  " + target.getName() + " dodges the attack!");
            return;
        }
        passToNext(incomingDamage, target);
    }
}