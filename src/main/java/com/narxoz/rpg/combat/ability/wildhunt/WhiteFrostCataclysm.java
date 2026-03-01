package main.java.com.narxoz.rpg.combat.ability.wildhunt;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class WhiteFrostCataclysm implements Ability {

    @Override
    public String getName() { return "White Frost Cataclysm"; }

    @Override
    public int getDamage() { return 160; }

    @Override
    public String getDescription() {
        return "Eredin opens a rift to the White Frost itself, unleashing apocalyptic cold that consumes everything.";
    }

    @Override
    public Ability clone() {
        return new WhiteFrostCataclysm();
    }
}
