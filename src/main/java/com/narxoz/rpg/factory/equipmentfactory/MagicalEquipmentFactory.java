package main.java.com.narxoz.rpg.factory.equipmentfactory;

import main.java.com.narxoz.rpg.equipment.Armor;
import main.java.com.narxoz.rpg.equipment.GriffinSet;
import main.java.com.narxoz.rpg.equipment.Staff;
import main.java.com.narxoz.rpg.equipment.Weapon;

public class MagicalEquipmentFactory implements EquipmentFactory {

    @Override
    public Weapon createWeapon() {
        return new Staff();
    }

    @Override
    public Armor createArmor() {
        return new GriffinSet();
    }

}
