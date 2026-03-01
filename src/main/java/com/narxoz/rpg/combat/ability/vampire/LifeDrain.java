package main.java.com.narxoz.rpg.combat.ability.vampire;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class LifeDrain implements Ability {

    // Percentage of damage dealt that heals the vampire
    public static final double HEAL_PERCENT = 0.30;

    @Override
    public String getName() { return "Life Drain"; }

    @Override
    public int getDamage() { return 70; }

    @Override
    public String getDescription() {
        return "Sinks fangs into the target, draining life force. Heals the vampire for 30% of damage dealt.";
    }

    @Override
    public Ability clone() {
        return new LifeDrain();
    }
}