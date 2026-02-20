package main.java.com.narxoz.rpg.combat.loot;

import main.java.com.narxoz.rpg.combat.LootTable;

import java.util.Arrays;
import java.util.List;

public class FireLootTable implements LootTable {

    @Override
    public List<String> getItems() {
        return Arrays.asList("Dragon Scale", "Ignium Crystal", "Salamander Tongue", "Flame Rune");
    }

    @Override
    public int getGoldDrop() { return 150; }

    @Override
    public int getExperienceDrop() { return 200; }

    @Override
    public LootTable clone() { return new FireLootTable(); }
}
