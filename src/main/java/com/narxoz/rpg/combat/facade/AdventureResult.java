package main.java.com.narxoz.rpg.combat.facade;

import java.util.List;

public class AdventureResult {

    private final int totalGold;
    private final int totalXP;
    private final List<String> inventory;

    public AdventureResult(int totalGold, int totalXP, List<String> inventory) {
        this.totalGold = totalGold;
        this.totalXP = totalXP;
        this.inventory = inventory;
    }

    public int getTotalGold() { return totalGold; }
    public int getTotalXP() { return totalXP; }
    public List<String> getInventory() { return inventory; }
}