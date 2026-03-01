package main.java.com.narxoz.rpg.factory.enemiesfactory;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.ability.vampire.BatSwarm;
import main.java.com.narxoz.rpg.combat.ability.vampire.BloodFrenzy;
import main.java.com.narxoz.rpg.combat.ability.vampire.LifeDrain;
import main.java.com.narxoz.rpg.loot.LootTable;
import main.java.com.narxoz.rpg.loot.VampireLootTable;
import main.java.com.narxoz.rpg.ai.AIBehavior;
import main.java.com.narxoz.rpg.ai.DefensiveAI;

import java.util.Arrays;
import java.util.List;

public class VampireFactory implements EnemyComponentFactory{

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(
                new BatSwarm(), new BloodFrenzy(), new LifeDrain()
        );
    }

    @Override
    public LootTable createLootTable() {
        return new VampireLootTable();
    }

    @Override
    public AIBehavior createAIBehavior() {
        return new DefensiveAI();
    }

}
