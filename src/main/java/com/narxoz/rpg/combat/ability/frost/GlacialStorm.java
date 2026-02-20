package main.java.com.narxoz.rpg.combat.ability.frost;

import main.java.com.narxoz.rpg.combat.Ability;

public class GlacialStorm implements Ability {
    private final String name = "Glacial Storm";
    private final int damage = 130;
    private final String description = "Summons a raging blizzard from the Northern peaks, freezing all enemies in a massive area and shattering them with ice shards.";

    @Override
    public String getName() { return name; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Ability clone() { return new GlacialStorm(); }
}
