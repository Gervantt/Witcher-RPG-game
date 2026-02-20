package main.java.com.narxoz.rpg.enemy;

import main.java.com.narxoz.rpg.combat.Ability;
import main.java.com.narxoz.rpg.combat.LootTable;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;

import java.util.List;
import java.util.Map;

public class Imlerith extends BossEnemy {

    public Imlerith(String name, int level, int health, int damage,
                    int defence, int agility,
                    List<Ability> abilities, LootTable lootTable, AIBehavior aiBehavior,
                    String loreDescription, List<String> phases,
                    Map<String, String> specialProperties) {
        super(name, level, health, damage, defence, agility, abilities,
                lootTable, aiBehavior, loreDescription, phases, specialProperties);
    }

    @Override
    public void display() {
        System.out.println("=== BOSS: Imlerith — General of the Wild Hunt ===");
        printBossStats();
    }

    @Override
    public Enemy clone() {
        return new Imlerith(name, level, health, damage, defence, agility,
                abilities, lootTable.clone(), aiBehavior,
                getLoreDescription(), getPhases(), getSpecialProperties());
    }
}
