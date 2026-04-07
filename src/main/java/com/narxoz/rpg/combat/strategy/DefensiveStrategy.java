package main.java.com.narxoz.rpg.combat.strategy;

public class DefensiveStrategy implements CombatStrategy {
    public int calculateDamage(int baseDamage) { return (int)(baseDamage * 0.7); }
    public int calculateDefense(int incomingDamage) { return (int)(incomingDamage * 0.5); }
    public String getStrategyName() { return "Defensive"; }
}