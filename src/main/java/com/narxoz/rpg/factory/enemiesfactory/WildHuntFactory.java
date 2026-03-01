package main.java.com.narxoz.rpg.factory.enemiesfactory;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.ability.wildhunt.BlinkStrike;
import main.java.com.narxoz.rpg.combat.ability.wildhunt.FrostNova;
import main.java.com.narxoz.rpg.combat.ability.wildhunt.WhiteFrostCataclysm;
import main.java.com.narxoz.rpg.loot.LootTable;
import main.java.com.narxoz.rpg.combat.ability.frost.AardFrostBlast;
import main.java.com.narxoz.rpg.combat.ability.frost.FrostArmor;
import main.java.com.narxoz.rpg.combat.ability.frost.GlacialStorm;

import main.java.com.narxoz.rpg.loot.WildHuntLootTable;
import main.java.com.narxoz.rpg.ai.AIBehavior;
import main.java.com.narxoz.rpg.ai.TacticalAI;

import java.util.Arrays;
import java.util.List;

public class WildHuntFactory implements EnemyComponentFactory{
    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(
                new BlinkStrike(), new FrostNova(), new WhiteFrostCataclysm()
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
