package main.java.com.narxoz.rpg.factory;

import main.java.com.narxoz.rpg.equipment.Armor;
import main.java.com.narxoz.rpg.equipment.Weapon;

public interface EquipmentFactory {

    Weapon createWeapon();
    Armor createArmor();

}
