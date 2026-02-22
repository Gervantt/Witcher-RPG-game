package main.java.com.narxoz.rpg.equipment.armor;

import main.java.com.narxoz.rpg.character.Character;

public interface Armor {

    int getDefence();
    String getArmorInfo();
    String getArmorType();

    void setDefence(int defence);
    String specialEnchantment(Character character);
}
