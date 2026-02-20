package main.java.com.narxoz.rpg.enemy;

import main.java.com.narxoz.rpg.combat.Ability;

import java.util.List;

public class Ghoul implements Enemy {
    private final String name;
    private final int level;
    private final int health;
    private final int damage;
    private final int defence;
    private final int agility;
    private final List<Ability> abilities;

    public Ghoul(String name, int level, int health, int damage, int defence, int agility, List<Ability> abilities) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.defence = defence;
        this.agility = agility;
        this.abilities = abilities;
    }

    @Override
    public String getName() { return name; }

    @Override
    public int getLevel() { return level; }

    @Override
    public int getHealth() { return health; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public int getDefence() { return defence; }

    @Override
    public int getAgility() { return agility; }

    @Override
    public List<Ability> getAbilities() { return abilities; }
}
