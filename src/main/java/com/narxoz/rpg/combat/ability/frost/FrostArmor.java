package main.java.com.narxoz.rpg.combat.ability.frost;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class FrostArmor implements Ability {
    private final String name = "Frost Armor";
    private final int damage = 25;
    private final String description = "Encases the caster in a layer of enchanted ice, deflecting blows and chilling any attacker that makes contact.";

    @Override
    public String getName() { return name; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Ability clone() { return new FrostArmor(); }
}
