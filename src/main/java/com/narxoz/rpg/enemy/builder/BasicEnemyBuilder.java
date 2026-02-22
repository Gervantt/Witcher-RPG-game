package main.java.com.narxoz.rpg.enemy.builder;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.loot.LootTable;
import main.java.com.narxoz.rpg.enemy.*;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;
import main.java.com.narxoz.rpg.factory.enemiesfactory.EnemyComponentFactory;

import java.util.List;

public class BasicEnemyBuilder implements EnemyBuilder{

   private String name;
   private int level;
   private int health;
   private int damage;
   private int defence;
   private int agility;

   private List<Ability> abilities;
   private LootTable lootTable;
   private AIBehavior   aiBehavior;
   private EnemyType type;

    @Override
    public EnemyBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public EnemyBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    @Override
    public EnemyBuilder withHealth(int health) {
        this.health = health;
        return this;
    }

    @Override
    public EnemyBuilder withDamage(int damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public EnemyBuilder withDefence(int defence) {
        this.defence = defence;
        return this;
    }

    @Override
    public EnemyBuilder withAgility(int agility) {
        this.agility = agility;
        return this;
    }

    @Override
    public EnemyBuilder withAbilities(List<Ability> abilities) {
        this.abilities = abilities;
        return this;
    }

    @Override
    public EnemyBuilder withLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
        return this;
    }

    @Override
    public EnemyBuilder withAIBehavior(AIBehavior aiBehavior) {
        this.aiBehavior = aiBehavior;
        return this;
    }

    @Override
    public EnemyBuilder withFactory(EnemyComponentFactory factory) {
        this.abilities = factory.createAbilities();
        this.lootTable = factory.createLootTable();
        this.aiBehavior = factory.createAIBehavior();
        return this;
    }

    public EnemyBuilder withType(EnemyType type) {
        this.type = type;
        return this;
    }

    @Override
    public void validate() {
        if (name == null || name.isBlank()) throw new IllegalStateException("Enemy name is mandatory.");
        if (level < 0)  throw new IllegalStateException("Enemy level is mandatory (>= 0).");
        if (health <= 0) throw new IllegalStateException("Enemy health must be > 0.");
        if (damage < 0)  throw new IllegalStateException("Enemy damage must be >= 0.");
        if (defence < 0)  throw new IllegalStateException("Enemy defence must be >= 0.");
        if (agility < 0)  throw new IllegalStateException("Enemy agility must be >= 0.");
        if (abilities == null || abilities.isEmpty()) throw new IllegalStateException("At least one ability is required.");
        if (lootTable == null) throw new IllegalStateException("LootTable is mandatory.");
        if (aiBehavior == null) throw new IllegalStateException("AIBehavior is mandatory.");
    }

    @Override
    public Enemy build() {
        validate();
        return switch(type){
            case DROWNER -> new Drowner(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior);
            case NEKKER -> new Nekker(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior);
            case WRAITH -> new Wraith(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior);
            case GHOUL -> new Ghoul(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior);
        };
    }
}
