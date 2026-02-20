package main.java.com.narxoz.rpg.combat.loot;

import main.java.com.narxoz.rpg.combat.LootTable;

import java.util.Arrays;
import java.util.List;

public class ShadowLootTable implements LootTable {

    @Override
    public List<String> getItems() {
        return Arrays.asList("Wraith Dust", "Ectoplasm", "Shadow Essence", "Dark Rune");
    }

    @Override
    public int getGoldDrop() { return 100; }

    @Override
    public int getExperienceDrop() { return 160; }

    @Override
    public LootTable clone() { return new ShadowLootTable(); }
}
