package main.java.com.narxoz.rpg.character;

public class Witcher implements Character {
    private final String name;
    private int health;
    private int strength;
    private int magic;
    private int agility;

    public Witcher(String name) {
        this.name = name;
        this.health = 200;
        this.strength = 80;
        this.magic = 40;
        this.agility = 70;
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
        System.out.println("=== The Witcher named " + name + " ===");
        System.out.println("Health: " + health
        + "\nStrength: " + strength
        + "\nEnchantment: " + magic
        + "\nAgility: " + agility
        );
    }

    @Override
    public void useSpecialAbility() {
        System.out.println("=== SPECIAL ABILITY USED ===");
        System.out.println(name + " drinks a battle potion that surges through his veins, boosting his Strength and Agility for a short time, but slightly reducing his Health due to toxicity.");
        health -= 20;
        strength += 15;
        agility += 15;
        magic += 15;
        System.out.println("=== Health: " + health+" ===");
        System.out.println("=== Strength: " + strength+" ===");
        System.out.println("=== Agility: " + agility+" ===");
        System.out.println("=== Magic: " + magic +" ===");
    }

}
