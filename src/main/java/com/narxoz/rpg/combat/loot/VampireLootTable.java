package main.java.com.narxoz.rpg.combat.loot;

import java.util.Arrays;
import java.util.List;

public class VampireLootTable implements LootTable {

    @Override
    public List<String> getItems() {
        return Arrays.asList(
                "Higher Vampire Trophy",
                "Vampire Fang",
                "Sanguine Crystal",
                "Midnight Cloak Fragment",
                "Essence of Darkness"
        );
    }

    @Override
    public int getGoldDrop() { return 250; }

    @Override
    public int getExperienceDrop() { return 350; }

    @Override
    public LootTable clone() { return new VampireLootTable(); }
}
