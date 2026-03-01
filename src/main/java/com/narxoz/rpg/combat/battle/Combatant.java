package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import java.util.List;

public interface Combatant {

    String getName();
    int getMaxHealth();
    int getCurrentHealth();
    int getAttackPower();
    int getMagicPower();
    int getDefencePower();
    int getAgilityValue();
    void takeDamage(int amount);
    void heal(int amount);
    boolean isAlive();
    List<Ability> getAbilities();

    boolean isBoss();
    String getPhaseName();
    boolean checkPhaseTransition();
    String getStatusBar();
}