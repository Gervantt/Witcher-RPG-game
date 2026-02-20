package main.java.com.narxoz.rpg.factory.abilitiesfactory;

import main.java.com.narxoz.rpg.combat.Ability;
import main.java.com.narxoz.rpg.combat.LootTable;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;

import java.util.List;

public interface EnemyComponentFactory {
    List<Ability> createAbilities();
    LootTable createLootTable();
    AIBehavior createAIBehavior();
}
