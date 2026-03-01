package main.java.com.narxoz.rpg.combat.ability.wildhunt;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class BlinkStrike implements Ability {

    @Override
    public String getName() { return "Blink Strike"; }

    @Override
    public int getDamage() { return 110; }

    @Override
    public String getDescription() {
        return "Eredin teleports behind his target and delivers a brutal overhead strike with his mace.";
    }

    @Override
    public Ability clone() {
        return new BlinkStrike();
    }

}