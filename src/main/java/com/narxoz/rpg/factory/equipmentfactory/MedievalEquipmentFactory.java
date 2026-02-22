package main.java.com.narxoz.rpg.factory.equipmentfactory;

import main.java.com.narxoz.rpg.equipment.armor.Armor;
import main.java.com.narxoz.rpg.equipment.weapon.SteelSword;
import main.java.com.narxoz.rpg.equipment.weapon.Weapon;
import main.java.com.narxoz.rpg.equipment.armor.WolvenSet;

public class MedievalEquipmentFactory implements EquipmentFactory {

    @Override
    public Weapon createWeapon() {
        return new SteelSword();
    }

    @Override
    public Armor createArmor() {
        return new WolvenSet();
    }

}
