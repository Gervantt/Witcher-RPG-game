package main.java.com.narxoz.rpg.factory;


import main.java.com.narxoz.rpg.equipment.Armor;
import main.java.com.narxoz.rpg.equipment.CrossBow;
import main.java.com.narxoz.rpg.equipment.FelineSet;
import main.java.com.narxoz.rpg.equipment.Weapon;

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
