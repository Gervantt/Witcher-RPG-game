package main.java.com.narxoz.rpg.combat.bridge;

public class FireEffect implements EffectImplementor {
    public String getEffectName() { return "Fire"; }
    public int applyEffect(int baseDamage) { return (int)(baseDamage * 1.3); }
    public String getDescription() { return "Scorching flames, +30% damage"; }
}