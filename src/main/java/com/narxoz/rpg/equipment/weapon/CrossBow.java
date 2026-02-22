package main.java.com.narxoz.rpg.equipment.weapon;

public class CrossBow implements Weapon {

    private int damage;
    private String description;
    private WeaponType type;

    public CrossBow() {
        damage = 75;
        description = "An elven crossbow made for fast shots. Light in the hands, deadly from far away.";
        type = WeaponType.RANGER;
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
