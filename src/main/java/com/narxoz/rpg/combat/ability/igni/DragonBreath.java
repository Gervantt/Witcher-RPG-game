package main.java.com.narxoz.rpg.combat.ability.igni;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class DragonBreath implements Ability {
    private final String name = "Dragon Breath";
    private final int damage = 150;
    private final String description = "Unleashes a devastating torrent of dragonfire amplified by the Igni sign, incinerating everything in a wide arc.";

    @Override
    public String getName() { return name; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Ability clone() { return new DragonBreath(); }
}
