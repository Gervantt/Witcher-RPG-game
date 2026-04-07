package main.java.com.narxoz.rpg.combat.strategy;

public class BerserkStrategy implements CombatStrategy {
    public int calculateDamage(int baseDamage) { return baseDamage * 2; }
    public int calculateDefense(int incomingDamage) { return incomingDamage; }
    public String getStrategyName() { return "Berserk"; }
}