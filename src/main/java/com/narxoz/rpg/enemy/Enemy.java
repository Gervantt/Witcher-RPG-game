package main.java.com.narxoz.rpg.enemy;

import main.java.com.narxoz.rpg.combat.Ability;

import java.util.List;

public interface Enemy {
    String getName();
    int getLevel();
    int getHealth();
    int getDamage();
    int getDefence();
    int getAgility();
    List<Ability> getAbilities();
}
