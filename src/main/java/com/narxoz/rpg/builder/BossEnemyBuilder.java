package main.java.com.narxoz.rpg.builder;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.loot.LootTable;
import main.java.com.narxoz.rpg.enemy.Enemy;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;
import main.java.com.narxoz.rpg.factory.enemiesfactory.EnemyComponentFactory;

import java.util.List;

public class BossEnemyBuilder implements EnemyBuilder{

    @Override
    public EnemyBuilder withName(String name) {
        return null;
    }

    @Override
    public EnemyBuilder withLevel(int level) {
        return null;
    }

    @Override
    public EnemyBuilder withHealth(int health) {
        return null;
    }

    @Override
    public EnemyBuilder withDamage(int damage) {
        return null;
    }

    @Override
    public EnemyBuilder withDefence(int defence) {
        return null;
    }

    @Override
    public EnemyBuilder withAgility(int agility) {
        return null;
    }

    @Override
    public EnemyBuilder withAbilities(List<Ability> abilities) {
        return null;
    }

    @Override
    public EnemyBuilder withLootTable(LootTable lootTable) {
        return null;
    }

    @Override
    public EnemyBuilder withAIBehavior(AIBehavior aiBehavior) {
        return null;
    }

    @Override
    public EnemyBuilder withFactory(EnemyComponentFactory factory) {
        return null;
    }

    @Override
    public Enemy build() {
        return null;
    }
}
