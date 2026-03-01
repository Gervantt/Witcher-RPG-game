package main.java.com.narxoz.rpg.loot;

import java.util.Arrays;
import java.util.List;

public class HigherVampireLootTable implements LootTable {

    private final List<String> items = Arrays.asList(
            "Higher Vampire Fang",
            "Blood-Stained Cloak",
            "Vampire Mutagen",
            "Ancient Toussaint Wine"
    );

    @Override
    public List<String> getItems() { return items; }

    @Override
    public int getGoldDrop() { return 500; }

    @Override
    public int getExperienceDrop() { return 2000; }

    @Override
    public LootTable clone() { return new HigherVampireLootTable(); }
}