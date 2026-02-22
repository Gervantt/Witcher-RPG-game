package main.java.com.narxoz.rpg.enemy.builder;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.loot.LootTable;
import main.java.com.narxoz.rpg.enemy.Enemy;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;
import main.java.com.narxoz.rpg.factory.enemiesfactory.EnemyComponentFactory;

import java.util.List;

public interface EnemyBuilder {

    EnemyBuilder withName(String name);
    EnemyBuilder withLevel(int level);
    EnemyBuilder withHealth(int health);
    EnemyBuilder withDamage(int damage);
    EnemyBuilder withDefence(int defence);
    EnemyBuilder withAgility(int agility);

    EnemyBuilder withAbilities(List<Ability> abilities);
    EnemyBuilder withLootTable(LootTable lootTable);
    EnemyBuilder withAIBehavior(AIBehavior aiBehavior);

    EnemyBuilder withFactory(EnemyComponentFactory factory);

    void validate();
    Enemy build();
}
