package main.java.com.narxoz.rpg.enemy;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.loot.LootTable;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;

import java.util.List;

public class Wraith extends Enemy{

    public Wraith(String name, int level, int health, int damage, int defence, int agility, List<Ability> abilities, LootTable lootTable, AIBehavior aiBehavior) {
        super(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior);
    }

    @Override
    public void display() {
        System.out.println("=== "+name+" OCCURED ===");
        printStats();
    }

    @Override
    public Enemy clone() {
        return new Wraith(name, level, health, damage, defence, agility,
                abilities, lootTable.clone(), aiBehavior);
    }
}
