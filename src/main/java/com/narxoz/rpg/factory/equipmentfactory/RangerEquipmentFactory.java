package main.java.com.narxoz.rpg.factory.equipmentfactory;


import main.java.com.narxoz.rpg.equipment.armor.Armor;
import main.java.com.narxoz.rpg.equipment.weapon.CrossBow;
import main.java.com.narxoz.rpg.equipment.armor.FelineSet;
import main.java.com.narxoz.rpg.equipment.weapon.Weapon;

public class RangerEquipmentFactory implements EquipmentFactory {

    @Override
    public Weapon createWeapon() {
        return new CrossBow();
    }

    @Override
    public Armor createArmor() {
        return new FelineSet();
    }

}
