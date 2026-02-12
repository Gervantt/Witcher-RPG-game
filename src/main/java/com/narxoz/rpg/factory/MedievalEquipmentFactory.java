package main.java.com.narxoz.rpg.factory;

import main.java.com.narxoz.rpg.equipment.Armor;
import main.java.com.narxoz.rpg.equipment.SteelSword;
import main.java.com.narxoz.rpg.equipment.Weapon;
import main.java.com.narxoz.rpg.equipment.WolvenSet;

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
