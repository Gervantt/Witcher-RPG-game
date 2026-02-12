package main.java.com.narxoz.rpg.equipment;

public class Staff implements Weapon{
    private int damage;
    private String description;
    private WeaponType type;

    public Staff() {
        damage = 50;
        description = "A mage staff that boosts spell power. Helps you focus and cast stronger magic.";
        type = WeaponType.MAGICAL;
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
