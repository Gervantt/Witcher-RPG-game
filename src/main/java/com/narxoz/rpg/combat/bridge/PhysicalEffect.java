package main.java.com.narxoz.rpg.combat.bridge;

public class PhysicalEffect implements EffectImplementor {
    public String getEffectName() { return "Physical"; }
    public int applyEffect(int baseDamage) { return baseDamage; }
    public String getDescription() { return "Raw physical force"; }
}