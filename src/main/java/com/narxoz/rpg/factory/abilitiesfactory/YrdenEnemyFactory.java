package main.java.com.narxoz.rpg.factory.abilitiesfactory;

import main.java.com.narxoz.rpg.combat.Ability;
import main.java.com.narxoz.rpg.combat.LootTable;
import main.java.com.narxoz.rpg.combat.ability.yrden.PhantomNova;
import main.java.com.narxoz.rpg.combat.ability.yrden.ShadowVeil;
import main.java.com.narxoz.rpg.combat.ability.yrden.YrdenTrap;
import main.java.com.narxoz.rpg.combat.loot.ShadowLootTable;
import main.java.com.narxoz.rpg.enemy.ai.TacticalAI;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;

import java.util.Arrays;
import java.util.List;

public class YrdenEnemyFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(new YrdenTrap(), new ShadowVeil(), new PhantomNova());
    }

    @Override
    public LootTable createLootTable() {
        return new ShadowLootTable();
    }

    @Override
    public AIBehavior createAIBehavior() {
        return new TacticalAI();
    }
}
