package main.java.com.narxoz.rpg.combat.strategy;

public class AggressiveStrategy implements CombatStrategy {
    public int calculateDamage(int baseDamage) { return (int)(baseDamage * 1.5); }
    public int calculateDefense(int incomingDamage) { return incomingDamage; }
    public String getStrategyName() { return "Aggressive"; }
}