package main.java.com.narxoz.rpg.combat.strategy;

public interface CombatStrategy {
    int calculateDamage(int baseDamage);
    int calculateDefense(int incomingDamage);
    String getStrategyName();
}