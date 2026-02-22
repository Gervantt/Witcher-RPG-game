package main.java.com.narxoz.rpg.combat.ability.igni;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class IgniBlast implements Ability {
    private final String name = "Igni Blast";
    private final int damage = 75;
    private final String description = "Channels the Igni sign into a scorching wave of fire, dealing AoE damage and applying burn to all nearby enemies.";

    @Override
    public String getName() { return name; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Ability clone() { return new IgniBlast(); }
}
