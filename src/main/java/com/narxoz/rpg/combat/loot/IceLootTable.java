package main.java.com.narxoz.rpg.combat.loot;

import main.java.com.narxoz.rpg.combat.LootTable;

import java.util.Arrays;
import java.util.List;

public class IceLootTable implements LootTable {

    @Override
    public List<String> getItems() {
        return Arrays.asList("Frost Crystal", "Ice Troll Hide", "Frozen Heart", "Frost Rune");
    }

    @Override
    public int getGoldDrop() { return 120; }

    @Override
    public int getExperienceDrop() { return 180; }

    @Override
    public LootTable clone() { return new IceLootTable(); }
}
