package main.java.com.narxoz.rpg.combat.bridge;

public interface EffectImplementor {
    String getEffectName();
    int applyEffect(int baseDamage);
    String getDescription();
}