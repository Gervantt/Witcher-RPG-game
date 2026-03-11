package main.java.com.narxoz.rpg.combat.bridge;

public class ShadowEffect implements EffectImplementor {
    public String getEffectName() { return "Shadow"; }
    public int applyEffect(int baseDamage) { return (int)(baseDamage * 1.2); }
    public String getDescription() { return "Dark energy, +20% damage"; }
}