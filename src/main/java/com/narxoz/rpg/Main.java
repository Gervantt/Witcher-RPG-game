package main.java.com.narxoz.rpg;

import main.java.com.narxoz.rpg.character.Character;
import main.java.com.narxoz.rpg.equipment.Armor;
import main.java.com.narxoz.rpg.equipment.Weapon;
import main.java.com.narxoz.rpg.factory.characterfactory.CharacterFactory;
import main.java.com.narxoz.rpg.factory.equipmentfactory.EquipmentFactory;
import main.java.com.narxoz.rpg.factory.equipmentfactory.MedievalEquipmentFactory;
import main.java.com.narxoz.rpg.factory.characterfactory.WitcherFactory;

public class Main {
    public static void main(String[] args) {
        CharacterFactory characterFactory = new WitcherFactory();
        Character character = characterFactory.createCharacter("Geralt");

        System.out.println("Created character: " + character.getName());
        character.displayStats();

        EquipmentFactory equipmentFactory = new MedievalEquipmentFactory();
        Weapon weapon = equipmentFactory.createWeapon();
        Armor armor = equipmentFactory.createArmor();

        character.equipArmor(armor);
        character.equipWeapon(weapon);

        System.out.println("\nWeapon: " + weapon.getClass().getSimpleName());
        System.out.println("Type: " + weapon.getWeaponType());
        System.out.println("Damage: " + weapon.getDamage());

        System.out.println("\nArmor: " + armor.getClass().getSimpleName());
        System.out.println("Type: " + armor.getArmorType());
        System.out.println("Defence: " + armor.getDefence());

        System.out.println("\nApplying armor enchantment...");
        System.out.println(armor.specialEnchantment(character));

        System.out.println("\nStats after enchantment:");
         character.displayStats();

        System.out.println("\nUsing special ability...");
        character.useSpecialAbility();
    }
}
