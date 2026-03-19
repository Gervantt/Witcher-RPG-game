package main.java.com.narxoz.rpg.combat.facade;

import main.java.com.narxoz.rpg.loot.LootTable;
import main.java.com.narxoz.rpg.combat.battle.*;
import main.java.com.narxoz.rpg.character.Character;
import main.java.com.narxoz.rpg.loot.*;

import java.util.*;

public class RewardService {

    private int totalGold = 100;
    private int totalXP = 0;
    private int level = 1;
    private final int XP_PER_LEVEL = 300;
    private final List<String> inventory = new ArrayList<>();

    private static final Map<String, LootTable> LOOT_MAP = new HashMap<>();

    static {
        LOOT_MAP.put("Nekker", new UndeadLootTable());
        LOOT_MAP.put("Ghoul", new UndeadLootTable());
        LOOT_MAP.put("Alghoul", new UndeadLootTable());
        LOOT_MAP.put("Drowner", new UndeadLootTable());
        LOOT_MAP.put("Noonwraith", new UndeadLootTable());
        LOOT_MAP.put("Nightwraith", new UndeadLootTable());
        LOOT_MAP.put("Katakan", new VampireLootTable());
        LOOT_MAP.put("Ekimmara", new VampireLootTable());
        LOOT_MAP.put("Eredin", new WildHuntLootTable());
        LOOT_MAP.put("Imlerith", new WildHuntLootTable());
        LOOT_MAP.put("Detlaff", new HigherVampireLootTable());
    }

    public int getGold() { return totalGold; }
    public int getTotalXP() { return totalXP; }
    public int getLevel() { return level; }
    public List<String> getInventory() { return inventory; }

    public void spendGold(int amount) { totalGold -= amount; }

    public void collectRewards(Combatant defeated) {
        if (defeated instanceof CombatGroup) {
            for (Combatant m : ((CombatGroup) defeated).getAllMembers()) {
                collectFromEnemy(m.getName());
            }
        } else {
            collectFromEnemy(defeated.getName());
        }
    }

    private void collectFromEnemy(String name) {
        for (Map.Entry<String, LootTable> entry : LOOT_MAP.entrySet()) {
            if (name.toLowerCase().contains(entry.getKey().toLowerCase())) {
                LootTable loot = entry.getValue();
                inventory.addAll(loot.getItems());
                totalGold += loot.getGoldDrop();
                totalXP += loot.getExperienceDrop();
                return;
            }
        }
        totalGold += 30;
        totalXP += 30;
    }

    public void checkLevelUp(Character hero) {
        int newLevel = 1 + (totalXP / XP_PER_LEVEL);

        while (level < newLevel) {
            level++;
            hero.setHealth(hero.getHealth() + 10);
            hero.setStrength(hero.getStrength() + 10);
            hero.setMagic(hero.getMagic() + 10);
            hero.setAgility(hero.getAgility() + 10);

            System.out.println("\n  ==========================================");
            System.out.println("  LEVEL UP! " + hero.getName() + " is now Level " + level + "!");
            System.out.println("  Health +" + 10 + ", Strength +" + 10 + ", Magic +" + 10 + ", Agility +" + 10);
            System.out.println("  ==========================================");
        }
    }

    public void printRewards(Combatant defeated, Character hero) {
        int goldBefore = totalGold;
        int xpBefore = totalXP;
        collectRewards(defeated);

        System.out.println("\n====================================");
        System.out.println("         CONTRACT REWARDS");
        System.out.println("====================================");
        System.out.println("  Gold earned: +" + (totalGold - goldBefore) + " crowns");
        System.out.println("  XP gained:   +" + (totalXP - xpBefore));
        System.out.println("  Total gold:  " + totalGold + " crowns");
        System.out.println("  Total XP:    " + totalXP + " (Level " + level + ")");
        System.out.println("====================================");

        checkLevelUp(hero);
    }

    public void printFinalSummary() {
        System.out.println("\n========================================");
        System.out.println("        WITCHER'S JOURNEY SUMMARY");
        System.out.println("========================================");
        System.out.println("  Level:      " + level);
        System.out.println("  Total Gold: " + totalGold + " crowns");
        System.out.println("  Total XP:   " + totalXP);
        System.out.println();
        if (inventory.isEmpty()) {
            System.out.println("  Inventory: empty");
        } else {
            System.out.println("  Inventory:");
            for (String item : inventory) System.out.println("    - " + item);
        }
        System.out.println("========================================");
    }
}