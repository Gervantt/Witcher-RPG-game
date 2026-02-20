package main.java.com.narxoz.rpg.combat.ability.yrden;

import main.java.com.narxoz.rpg.combat.Ability;

public class ShadowVeil implements Ability {
    private final String name = "Shadow Veil";
    private final int damage = 0;
    private final String description = "Melts into the shadows like a wraith, becoming nearly undetectable. The next strike from hiding deals devastating bonus damage.";

    @Override
    public String getName() { return name; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Ability clone() { return new ShadowVeil(); }
}
