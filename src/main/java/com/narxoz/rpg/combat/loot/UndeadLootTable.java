package main.java.com.narxoz.rpg.combat.loot;

import java.util.Arrays;
import java.util.List;

public class UndeadLootTable implements LootTable {

    @Override
    public List<String> getItems() {
        return Arrays.asList(
                "Cursed Bones",
                "Necromantic Dust",
                "Ancient Grave Ring",
                "Soul Shard"
        );
    }

    @Override
    public int getGoldDrop() { return 80; }

    @Override
    public int getExperienceDrop() { return 120; }

    @Override
    public LootTable clone() { return new UndeadLootTable(); }
}
