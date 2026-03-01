package main.java.com.narxoz.rpg.combat.ability.undead;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class BloodThirst implements Ability {

    // Damage boost multiplier when HP is below threshold
    public static final double DAMAGE_BOOST = 0.15;

    // HP percentage threshold that triggers the frenzy
    public static final double HP_THRESHOLD = 0.30;

    @Override
    public String getName() { return "Blood Thirst"; }

    @Override
    public int getDamage() { return 35; }

    @Override
    public String getDescription() {
        return "The scent of blood drives the creature into a frenzy. Damage increases by 15% when HP drops below 30%.";
    }

    @Override
    public Ability clone() {
        return new BloodThirst();
    }
}