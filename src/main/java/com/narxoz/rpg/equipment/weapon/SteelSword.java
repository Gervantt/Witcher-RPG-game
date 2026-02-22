package main.java.com.narxoz.rpg.equipment.weapon;


public class SteelSword implements Weapon {
    private int damage;
    private String description;
    private WeaponType type;

    public SteelSword() {
        damage = 100;
        description = "A Witcher's steel blade. Balanced, sharp, and made to cut through claws and armor.";
        type = WeaponType.MEDIEVAL;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String getWeaponInfo() {
        return description;
    }

    @Override
    public String getWeaponType() {
        return type.toString().toLowerCase();
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

}
