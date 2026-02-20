package main.java.com.narxoz.rpg.combat.ability.yrden;

import main.java.com.narxoz.rpg.combat.Ability;

public class YrdenTrap implements Ability {
    private final String name = "Yrden Trap";
    private final int damage = 85;
    private final String description = "Casts a Yrden magical sigil that drains the life force of all who step within it, leaving survivors blinded and disoriented.";

    @Override
    public String getName() { return name; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Ability clone() { return new YrdenTrap(); }
}
