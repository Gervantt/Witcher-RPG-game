package main.java.com.narxoz.rpg.factory.enemiesfactory;

import main.java.com.narxoz.rpg.combat.Ability;
import main.java.com.narxoz.rpg.combat.LootTable;
import main.java.com.narxoz.rpg.combat.ability.igni.DragonBreath;
import main.java.com.narxoz.rpg.combat.ability.igni.IgniBlast;
import main.java.com.narxoz.rpg.combat.ability.igni.IgniShield;
import main.java.com.narxoz.rpg.combat.ability.yrden.PhantomNova;
import main.java.com.narxoz.rpg.combat.ability.yrden.ShadowVeil;
import main.java.com.narxoz.rpg.combat.ability.yrden.YrdenTrap;
import main.java.com.narxoz.rpg.combat.loot.UndeadLootTable;
import main.java.com.narxoz.rpg.combat.loot.VampireLootTable;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;
import main.java.com.narxoz.rpg.enemy.ai.AggressiveAI;
import main.java.com.narxoz.rpg.enemy.ai.DefensiveAI;

import java.util.Arrays;
import java.util.List;

public class VampireFactory implements EnemyComponentFactory{

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(
                new DragonBreath(),
                new IgniBlast(),
                new IgniShield()
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
