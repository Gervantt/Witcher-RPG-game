package main.java.com.narxoz.rpg.combat.loot;

import java.util.Arrays;
import java.util.List;

public class WildHuntLootTable implements LootTable {

    @Override
    public List<String> getItems() {
        return Arrays.asList(
                "Wild Hunt Warrior Trophy",
                "Voidmetal Shard",
                "Aen Elle Rune Stone",
                "Spectral Dust",
                "Naglfar's Anchor Fragment"
        );
    }

    @Override
    public int getGoldDrop() { return 300; }

    @Override
    public int getExperienceDrop() { return 400; }

    @Override
    public LootTable clone() { return new WildHuntLootTable(); }
}
