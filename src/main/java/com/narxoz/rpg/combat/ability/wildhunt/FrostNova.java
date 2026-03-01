package main.java.com.narxoz.rpg.combat.ability.wildhunt;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class FrostNova implements Ability {

    @Override
    public String getName() { return "Frost Nova"; }

    @Override
    public int getDamage() { return 95; }

    @Override
    public String getDescription() {
        return "Eredin channels the power of the White Frost, releasing a devastating wave of ice in all directions.";
    }

    @Override
    public Ability clone() {
        return new FrostNova();
    }

}