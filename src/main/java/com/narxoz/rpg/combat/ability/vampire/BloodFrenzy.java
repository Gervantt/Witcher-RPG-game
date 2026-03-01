package main.java.com.narxoz.rpg.combat.ability.vampire;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class BloodFrenzy implements Ability {

    @Override public String getName() { return "Blood Frenzy"; }

    @Override public int getDamage() { return 135; }

    @Override
    public String getDescription() {
        return "Detlaff transforms partially, slashing with elongated claws in a blood-fueled rampage.";
    }

    @Override
    public Ability clone() { return new BloodFrenzy(); }

}