package main.java.com.narxoz.rpg.combat.ability.vampire;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class BatSwarm implements Ability {

    @Override
    public String getName() { return "Bat Swarm"; }

    @Override
    public int getDamage() { return 90; }

    @Override
    public String getDescription() {
        return "Dissolves into a swarm of bats that engulf and tear at the target. A devastating multi-hit attack.";
    }

    @Override
    public Ability clone() {
        return new BatSwarm();
    }
}