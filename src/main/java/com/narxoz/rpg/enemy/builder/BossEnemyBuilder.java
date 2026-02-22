package main.java.com.narxoz.rpg.enemy.builder;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.loot.LootTable;
import main.java.com.narxoz.rpg.enemy.*;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;
import main.java.com.narxoz.rpg.factory.enemiesfactory.EnemyComponentFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BossEnemyBuilder implements EnemyBuilder{

    private String name;
    private int level;
    private int health;
    private int damage;
    private int defence;
    private int agility;
    private List<Ability> abilities;
    private LootTable lootTable;
    private AIBehavior aiBehavior;
    private BossType bossType;
    private String loreDescription;
    private final List<String> phases = new ArrayList<>();
    private final Map<String, String> specialProps = new HashMap<>();

    @Override
    public BossEnemyBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public BossEnemyBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    @Override
    public BossEnemyBuilder withHealth(int health) {
        this.health = health;
        return this;
    }

    @Override
    public BossEnemyBuilder withDamage(int damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public BossEnemyBuilder withDefence(int defence) {
        this.defence = defence;
        return this;
    }

    @Override
    public BossEnemyBuilder withAgility(int agility) {
        this.agility = agility;
        return this;
    }

    @Override
    public BossEnemyBuilder withAbilities(List<Ability> abilities) {
        this.abilities = abilities;
        return this;
    }

    @Override
    public BossEnemyBuilder withLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
        return this;
    }

    @Override
    public BossEnemyBuilder withAIBehavior(AIBehavior aiBehavior) {
        this.aiBehavior = aiBehavior;
        return this;
    }

    @Override
    public BossEnemyBuilder withFactory(EnemyComponentFactory factory) {
        this.abilities  = factory.createAbilities();
        this.lootTable  = factory.createLootTable();
        this.aiBehavior = factory.createAIBehavior();
        return this;
    }

    public BossEnemyBuilder withLoreDescription(String loreDescription) {
        this.loreDescription = loreDescription;
        return this;
    }

    public BossEnemyBuilder addPhase(String phase) {
        this.phases.add(phase);
        return this;
    }

    public BossEnemyBuilder addSpecialProperty(String key, String value) {
        this.specialProps.put(key, value);
        return this;
    }

    public BossEnemyBuilder withType(BossType bossType) {
        this.bossType = bossType;
        return this;
    }

    @Override
    public void validate() {
        if (name == null || name.isBlank()) throw new IllegalStateException("Boss name is mandatory.");
        if (level < 0)  throw new IllegalStateException("Boss level is mandatory (>= 0).");
        if (health <= 0) throw new IllegalStateException("Boss health must be > 0.");
        if (damage < 0)  throw new IllegalStateException("Boss damage must be >= 0.");
        if (defence < 0)  throw new IllegalStateException("Boss defence must be >= 0.");
        if (agility < 0)  throw new IllegalStateException("Boss agility must be >= 0.");
        if (abilities == null || abilities.isEmpty())
            throw new IllegalStateException("At least one ability required. Use withFactory() or withAbilities().");
        if (lootTable == null) throw new IllegalStateException("LootTable is mandatory.");
        if (aiBehavior == null) throw new IllegalStateException("AIBehavior is mandatory.");
        if (phases.isEmpty())
            throw new IllegalStateException("Boss must have at least one phase. Use addPhase().");
    }

    @Override
    public BossEnemy build() {
        validate();
        return switch (bossType) {
            case IMLERITH -> new Imlerith(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior, loreDescription, phases, specialProps);
            case EREDIN -> new Eredin(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior, loreDescription, phases, specialProps);
            case DETLAFF -> new Detlaff(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior, loreDescription, phases, specialProps);
        };
    }
}
