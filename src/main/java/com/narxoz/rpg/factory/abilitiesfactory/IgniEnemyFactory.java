package main.java.com.narxoz.rpg.factory.abilitiesfactory;

import main.java.com.narxoz.rpg.combat.Ability;
import main.java.com.narxoz.rpg.combat.LootTable;
import main.java.com.narxoz.rpg.combat.ability.igni.DragonBreath;
import main.java.com.narxoz.rpg.combat.ability.igni.IgniBlast;
import main.java.com.narxoz.rpg.combat.ability.igni.IgniShield;
import main.java.com.narxoz.rpg.combat.loot.FireLootTable;
import main.java.com.narxoz.rpg.enemy.ai.AggressiveAI;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;

import java.util.Arrays;
import java.util.List;

public class IgniEnemyFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(new IgniBlast(), new IgniShield(), new DragonBreath());
    }

    @Override
    public LootTable createLootTable() {
        return new FireLootTable();
    }

    @Override
    public AIBehavior createAIBehavior() {
        return new AggressiveAI();
    }
}
