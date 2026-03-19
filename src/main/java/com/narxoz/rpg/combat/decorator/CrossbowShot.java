package main.java.com.narxoz.rpg.combat.decorator;

public class CrossbowShot implements AttackAction {

    private int baseDamage;

    public CrossbowShot(int baseDamage) {
        this.baseDamage = (int)(baseDamage * 0.7);
    }

    public int getDamage() { return baseDamage; }

    public String getDescription() { return "Crossbow Shot"; }
}