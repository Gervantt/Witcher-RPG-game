package main.java.com.narxoz.rpg.combat.ability.igni;

import main.java.com.narxoz.rpg.combat.Ability;

public class IgniShield implements Ability {
    private final String name = "Igni Shield";
    private final int damage = 20;
    private final String description = "Conjures a blazing barrier of Igni fire that deflects incoming attacks and burns those who strike the caster.";

    @Override
    public String getName() { return name; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Ability clone() { return new IgniShield(); }
}
