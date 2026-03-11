package main.java.com.narxoz.rpg.combat.bridge;

public class IceEffect implements EffectImplementor {
    public String getEffectName() { return "Ice"; }
    public int applyEffect(int baseDamage) { return (int)(baseDamage * 1.1); }
    public String getDescription() { return "Freezing cold, +10% damage"; }
}


