package main.java.com.narxoz.rpg.enemy;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.loot.LootTable;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;

import java.util.List;

public class Ghoul extends Enemy {

    public Ghoul(String name, int level, int health, int damage,
                 int defence, int agility,
                 List<Ability> abilities, LootTable lootTable, AIBehavior aiBehavior) {
        super(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior);
    }

    @Override
    public void display() {
        System.out.println("=== GHOUL OCCURED ===");
        printStats();
    }

    @Override
    public Enemy clone() {
        return new Ghoul(name, level, health, damage, defence, agility,
                abilities, lootTable.clone(), aiBehavior);
    }

}