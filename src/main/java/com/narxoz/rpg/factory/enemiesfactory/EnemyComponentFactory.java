package main.java.com.narxoz.rpg.factory.enemiesfactory;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.loot.LootTable;
import main.java.com.narxoz.rpg.ai.AIBehavior;

import java.util.List;

public interface EnemyComponentFactory {
    List<Ability> createAbilities();
    LootTable createLootTable();
    AIBehavior createAIBehavior();
}
