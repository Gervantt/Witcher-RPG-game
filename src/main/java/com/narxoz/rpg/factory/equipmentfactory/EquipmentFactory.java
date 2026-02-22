package main.java.com.narxoz.rpg.factory.equipmentfactory;

import main.java.com.narxoz.rpg.equipment.armor.Armor;
import main.java.com.narxoz.rpg.equipment.weapon.Weapon;

public interface EquipmentFactory {

    Weapon createWeapon();
    Armor createArmor();

}
