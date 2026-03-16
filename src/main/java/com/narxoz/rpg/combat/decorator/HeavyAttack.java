package main.java.com.narxoz.rpg.combat.decorator;

public class HeavyAttack implements AttackAction {

    private int baseDamage;

    public HeavyAttack(int baseDamage) {
        this.baseDamage = (int)(baseDamage * 1.5);
    }

    public int getDamage() { return baseDamage; }

    public String getDescription() { return "Heavy Attack"; }
}