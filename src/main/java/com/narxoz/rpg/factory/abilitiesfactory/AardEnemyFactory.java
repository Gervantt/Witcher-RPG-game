package main.java.com.narxoz.rpg.factory.abilitiesfactory;

import main.java.com.narxoz.rpg.combat.Ability;
import main.java.com.narxoz.rpg.combat.LootTable;
import main.java.com.narxoz.rpg.combat.ability.frost.AardFrostBlast;
import main.java.com.narxoz.rpg.combat.ability.frost.FrostArmor;
import main.java.com.narxoz.rpg.combat.ability.frost.GlacialStorm;
import main.java.com.narxoz.rpg.combat.loot.IceLootTable;
import main.java.com.narxoz.rpg.enemy.ai.DefensiveAI;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;

import java.util.Arrays;
import java.util.List;

public class AardEnemyFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(new AardFrostBlast(), new FrostArmor(), new GlacialStorm());
    }

    @Override
    public LootTable createLootTable() {
        return new IceLootTable();
    }

    @Override
    public AIBehavior createAIBehavior() {
        return new DefensiveAI();
    }
}
