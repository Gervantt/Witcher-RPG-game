package main.java.com.narxoz.rpg.enemy;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.loot.LootTable;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;

import java.util.List;
import java.util.Map;

public class Eredin extends BossEnemy{

    protected Eredin(String name, int level, int health, int damage, int defence, int agility, List<Ability> abilities, LootTable lootTable, AIBehavior aiBehavior, String loreDescription, List<String> phases, Map<String, String> specialProperties) {
        super(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior, loreDescription, phases, specialProperties);
    }

    @Override
    public void display() {
        System.out.println("=== RAID BOSS: Eredin Bréacc Glas — King of the Wild Hunt ===");
        printBossStats();
    }

    @Override
    public Enemy clone() {
        return new Eredin(name, level, health, damage, defence, agility,
                abilities, lootTable.clone(), aiBehavior,
                getLoreDescription(), getPhases(), getSpecialProperties());
    }
}
