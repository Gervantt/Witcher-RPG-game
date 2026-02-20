package main.java.com.narxoz.rpg.combat;

import java.util.List;

public interface LootTable {
    List<String> getItems();
    int getGoldDrop();
    int getExperienceDrop();
    LootTable clone();
}
