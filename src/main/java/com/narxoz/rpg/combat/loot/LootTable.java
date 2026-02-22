package main.java.com.narxoz.rpg.combat.loot;

import java.util.List;

public interface LootTable {
    List<String> getItems();
    int getGoldDrop();
    int getExperienceDrop();
    LootTable clone();
}
