package main.java.com.narxoz.rpg.combat.ability;

public interface Ability {
    String getName();
    int getDamage();
    String getDescription();
    Ability clone();
}
