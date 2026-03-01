package main.java.com.narxoz.rpg.enemy;

import main.java.com.narxoz.rpg.ai.AIBehavior;
import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.loot.LootTable;

import java.util.List;

public class Katakan extends Enemy {

    public Katakan(String name, int level, int health, int damage,
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
        return new Katakan(name, level, health, damage, defence, agility,
                abilities, lootTable.clone(), aiBehavior);
    }

}
