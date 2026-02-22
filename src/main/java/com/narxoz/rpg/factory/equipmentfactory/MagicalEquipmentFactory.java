package main.java.com.narxoz.rpg.factory.equipmentfactory;

import main.java.com.narxoz.rpg.equipment.armor.Armor;
import main.java.com.narxoz.rpg.equipment.armor.GriffinSet;
import main.java.com.narxoz.rpg.equipment.weapon.Staff;
import main.java.com.narxoz.rpg.equipment.weapon.Weapon;

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
