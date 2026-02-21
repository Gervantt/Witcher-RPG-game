package main.java.com.narxoz.rpg.factory.enemiesfactory;

import main.java.com.narxoz.rpg.combat.Ability;
import main.java.com.narxoz.rpg.combat.LootTable;
import main.java.com.narxoz.rpg.combat.ability.frost.AardFrostBlast;
import main.java.com.narxoz.rpg.combat.ability.frost.FrostArmor;
import main.java.com.narxoz.rpg.combat.ability.frost.GlacialStorm;

import main.java.com.narxoz.rpg.combat.loot.WildHuntLootTable;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;
import main.java.com.narxoz.rpg.enemy.ai.AggressiveAI;
import main.java.com.narxoz.rpg.enemy.ai.TacticalAI;

import java.util.Arrays;
import java.util.List;

public class WildHuntFactory implements EnemyComponentFactory{
    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(
                new AardFrostBlast(),
                new FrostArmor(),
                new GlacialStorm()
        );
    }

    @Override
    public LootTable createLootTable() {
        return new WildHuntLootTable();
    }

    @Override
    public AIBehavior createAIBehavior() {
        return new TacticalAI();
    }


}
