package main.java.com.narxoz.rpg.character;

public class ElfArcher implements Character {
    private final String name;
    private int health;
    private int strength;
    private int magic;
    private int agility;

    public ElfArcher(String name) {
        this.name = name;
        this.health = 150;
        this.strength = 60;
        this.magic = 10;
        this.agility = 80;
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

    @Override
    public void setHealth(int health) {
        this.health = health;
    }
    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public void setMagic(int magic) {
        this.magic = magic;
    }

    @Override
    public void setAgility(int agility) {
        this.agility = agility;
    }

    @Override
    public void displayStats() {
        System.out.println("=== The Elf Archer named " + name + " ===");
        System.out.println("Health: " + health
                + "\nStrength: " + strength
                + "\nEnchantment: " + magic
                + "\nAgility: " + agility
        );
    }

    @Override
    public void useSpecialAbility() {
        System.out.println("=== SPECIAL ABILITY USED ===");
        System.out.println(name + " enters perfect concentration, increasing Accuracy and Agility for a short time and dealing bonus damage with ranged attacks.");
        agility += 15;
        strength += 15;
        System.out.println("=== Health: " + health+" ===");
        System.out.println("=== Strength: " + strength+" ===");
        System.out.println("=== Agility: " + agility+" ===");
        System.out.println("=== Magic: " + magic +" ===");
    }
}
