package main.java.com.narxoz.rpg.character;

import main.java.com.narxoz.rpg.equipment.armor.Armor;
import main.java.com.narxoz.rpg.equipment.weapon.Weapon;

public class Mage implements Character{
    private final String name;
    private int health;
    private int strength;
    private int magic;
    private int agility;
    private Weapon weapon;
    private Armor armor;

    public Mage(String name) {
        this.name = name;
        this.health = 120;
        this.strength = 40;
        this.magic = 100;
        this.agility = 50;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getMagic() {
        return magic;
    }

    @Override
    public int getAgility() {
        return agility;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    @Override
    public void displayStats() {
        System.out.println("=== The Mage named " + name + " ===");
        System.out.println("Health: " + health
                + "\nStrength: " + strength
                + "\nEnchantment: " + magic
                + "\nAgility: " + agility
        );
    }

    @Override
    public void useSpecialAbility() {
        System.out.println("=== SPECIAL ABILITY USED ===");
        System.out.println(name + " sends a Fireball that wipes out everything in the radius of 150 meters, but loses the Magic Powers for a short time");
        magic -= 80;
        System.out.println("=== Health: " + health+" ===");
        System.out.println("=== Strength: " + strength+" ===");
        System.out.println("=== Agility: " + agility+" ===");
        System.out.println("=== Magic: " + magic +" ===");
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        System.out.println("=== "+name+" equipped new weapon! ===");
        System.out.println("=== "+weapon.getWeaponInfo()+" ===");
        this.weapon = weapon;
        strength += weapon.getDamage();
        System.out.println("=== STRENGTH INCREASED: " + strength+" ===");
    }

    @Override
    public void equipArmor(Armor armor) {
        System.out.println("=== "+name+" equipped new armor! ===");
        System.out.println("=== "+armor.getArmorInfo()+" ===");
        this.armor = armor;
        health += armor.getDefence();
        System.out.println("=== HEALTH INCREASED: " + health+" ===");
    }
}
