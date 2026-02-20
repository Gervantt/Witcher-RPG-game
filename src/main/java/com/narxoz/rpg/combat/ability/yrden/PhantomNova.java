package main.java.com.narxoz.rpg.combat.ability.yrden;

import main.java.com.narxoz.rpg.combat.Ability;

public class PhantomNova implements Ability {
    private final String name = "Phantom Nova";
    private final int damage = 140;
    private final String description = "Erupts a Yrden circle into a shockwave of spectral dark energy, tearing through the souls of all nearby enemies.";

    @Override
    public String getName() { return name; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Ability clone() { return new PhantomNova(); }
}
