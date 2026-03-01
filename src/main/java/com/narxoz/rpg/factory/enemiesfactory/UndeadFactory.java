package main.java.com.narxoz.rpg.factory.enemiesfactory;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.ability.undead.BloodThirst;
import main.java.com.narxoz.rpg.combat.ability.undead.GhostMode;
import main.java.com.narxoz.rpg.loot.LootTable;
import main.java.com.narxoz.rpg.combat.ability.yrden.PhantomNova;
import main.java.com.narxoz.rpg.combat.ability.yrden.ShadowVeil;
import main.java.com.narxoz.rpg.combat.ability.yrden.YrdenTrap;
import main.java.com.narxoz.rpg.loot.UndeadLootTable;
import main.java.com.narxoz.rpg.ai.AIBehavior;
import main.java.com.narxoz.rpg.ai.AggressiveAI;

import java.util.Arrays;
import java.util.List;

public class UndeadFactory implements EnemyComponentFactory{

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(
                new BloodThirst(), new GhostMode()
        );
    }

    @Override
    public LootTable createLootTable() {
        return new UndeadLootTable();
    }

    @Override
    public AIBehavior createAIBehavior() {
        return new AggressiveAI();
    }

}
