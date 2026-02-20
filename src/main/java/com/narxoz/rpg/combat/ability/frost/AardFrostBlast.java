package main.java.com.narxoz.rpg.combat.ability.frost;

import main.java.com.narxoz.rpg.combat.Ability;

public class AardFrostBlast implements Ability {
    private final String name = "Aard Frost Blast";
    private final int damage = 65;
    private final String description = "A powerful blast of frozen air channeling the Aard sign's force, dealing damage and slowing all struck enemies.";

    @Override
    public String getName() { return name; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Ability clone() { return new AardFrostBlast(); }
}
