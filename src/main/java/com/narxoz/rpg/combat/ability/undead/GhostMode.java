package main.java.com.narxoz.rpg.combat.ability.undead;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class GhostMode implements Ability {

    public static final double DAMAGE_REDUCTION = 0.20;

    @Override
    public String getName() { return "Ghost Mode"; }

    @Override
    public int getDamage() { return 45; }

    @Override
    public String getDescription() {
        return "Phases into the spectral plane, reducing all incoming damage by 20%. Can also lash out with a ghostly touch.";
    }

    @Override
    public Ability clone() { return new GhostMode(); }
}