package main.java.com.narxoz.rpg.combat.decorator;

public class BasicAttack implements AttackAction {

    private int baseDamage;

    public BasicAttack(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getDamage() { return baseDamage; }

    public String getDescription() { return "Sword Strike"; }
}