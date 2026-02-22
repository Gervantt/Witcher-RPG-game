package main.java.com.narxoz.rpg.enemy;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.loot.LootTable;
import main.java.com.narxoz.rpg.enemy.ai.AIBehavior;

import java.util.List;
import java.util.Map;

public abstract class BossEnemy extends Enemy{

    private final String loreDescription;
    private final List<String> phases;
    private final Map<String, String> specialProperties;
    private int currentPhase;

    protected BossEnemy(String name, int level, int health, int damage, int defence, int agility, List<Ability> abilities, LootTable lootTable, AIBehavior aiBehavior, String loreDescription, List<String> phases, Map<String, String> specialProperties) {
        super(name, level, health, damage, defence, agility, abilities, lootTable, aiBehavior);

        this.loreDescription = loreDescription;
        this.phases = phases;
        this.specialProperties = specialProperties;
        this.currentPhase = 1;
    }
    public String getLoreDescription() {
        return loreDescription;
    }
    public List<String> getPhases() {
        return phases;
    }
    public Map<String, String> getSpecialProperties() {
        return specialProperties;
    }
    public int getCurrentPhase() {
        return currentPhase;
    }

    public String getCurrentPhaseDescription() {
        int idx = currentPhase - 1;
        return (idx >= 0 && idx < phases.size()) ? phases.get(idx) : "Unknown Phase";
    }

    public void advancePhase() {
        if (currentPhase < phases.size()) {
            currentPhase++;
            System.out.println("=== "+ name + " enters Phase " + currentPhase
                    + ": " + getCurrentPhaseDescription()+" ===");
        } else {
            System.out.println(name + " is already in its final phase!");
        }
    }

    public String getSpecialProperty(String key) {
        return specialProperties.getOrDefault(key, "N/A");
    }

    protected void printBossStats() {
        printStats();
        System.out.println("Phases: " + phases.size());
        for (int i = 0; i < phases.size(); i++) {
            System.out.println("  Phase " + (i + 1) + ": " + phases.get(i));
        }
        if (!specialProperties.isEmpty()) {
            System.out.println("Special Properties: ");
            specialProperties.forEach((k, v) ->
                    System.out.println("  " + k + ": " + v)
            );
        }
        System.out.println("Lore: " + loreDescription);
    }
}
