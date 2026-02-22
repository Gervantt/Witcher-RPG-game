package main.java.com.narxoz.rpg.character;

import main.java.com.narxoz.rpg.equipment.armor.Armor;
import main.java.com.narxoz.rpg.equipment.weapon.Weapon;

public interface Character {
    String getName();
    int getHealth();
    int getStrength();
    int getMagic();
    int getAgility();

    // I added setters too so that when the Armor or Weapon gets equipped, I can call them and increase the stats of the character
    void setAgility(int agility);
    void setStrength(int strength);
    void setMagic(int magic);
    void setHealth(int health);

    void displayStats();
    void useSpecialAbility();

    void equipWeapon(Weapon weapon);
    void equipArmor(Armor armor);
}
