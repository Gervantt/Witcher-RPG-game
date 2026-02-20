package main.java.com.narxoz.rpg.enemy;

import main.java.com.narxoz.rpg.combat.Ability;
import main.java.com.narxoz.rpg.combat.LootTable;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;

import java.util.List;

public class Drowner extends Enemy {

    public Drowner(String name, int level, int health, int damage,
                   int defence, int agility,
                   List<Ability> abilities, LootTable lootTable, AIBehavior aiBehavior) {
        super(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior);
    }

    @Override
    public void display() {
        System.out.println("=== "+name+" OCCURED ===");
        printStats();
    }

    @Override
    public Enemy clone() {
        return new Drowner(name, level, health, damage, defence, agility,
                abilities, lootTable.clone(), aiBehavior);
    }
}
