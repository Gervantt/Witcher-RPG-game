package main.java.com.narxoz.rpg.enemy;

import main.java.com.narxoz.rpg.combat.Ability;
import main.java.com.narxoz.rpg.combat.LootTable;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;

import java.util.List;

public abstract class Enemy {
    protected final String name;
    protected final int level;
    protected final int health;
    protected final int damage;
    protected final int defence;
    protected final int agility;

    protected final List<Ability> abilities;
    protected final LootTable lootTable;
    protected final AIBehavior aiBehavior;

    protected Enemy(String name, int level, int health, int damage, int defence, int agility, List<Ability> abilities, LootTable lootTable, AIBehavior aiBehavior) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.defence = defence;
        this.agility = agility;
        this.abilities = abilities;
        this.lootTable = lootTable;
        this.aiBehavior = aiBehavior;
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getDamage() { return damage; }
    public int getDefence() { return defence; }
    public int getAgility() { return agility; }

    public List<Ability> getAbilities() { return abilities; }
    public Ability getAbility(int index) {
        if (index < 0 || index >= abilities.size())
            throw new IndexOutOfBoundsException("No ability at index " + index);
        return abilities.get(index);
    }
    public LootTable getLootTable() { return lootTable; }
    public AIBehavior getAIBehavior() { return aiBehavior; }

    public abstract void display();
    public abstract Enemy clone();

    protected void printStats() {
        System.out.println("=== " + name + " [Lvl " + level + "] ===");
        System.out.println("Health: " + health
                + "\nDamage: " + damage
                + "\nDefence: " + defence
                + "\nAgility: " + agility
        );
        System.out.println("Abilities: ");
        abilities.forEach(a ->
                System.out.println("  - " + a.getName() + " (dmg " + a.getDamage() + ")")
        );
        System.out.println("Loot: ");
        System.out.println("  Gold: " + lootTable.getGoldDrop() + " | XP: " + lootTable.getExperienceDrop());
        lootTable.getItems().forEach(item -> System.out.println("  - " + item));
        System.out.println("AI Behaviour: " + aiBehavior.getBehaviorType());
    }

}
