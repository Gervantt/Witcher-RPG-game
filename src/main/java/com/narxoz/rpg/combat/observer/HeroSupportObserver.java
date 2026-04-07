package main.java.com.narxoz.rpg.combat.observer;

import main.java.com.narxoz.rpg.combat.battle.Combatant;

public class HeroSupportObserver implements GameObserver {

    private Combatant hero;
    private boolean usedEmergencyHeal = false;

    public HeroSupportObserver(Combatant hero) {
        this.hero = hero;
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.HERO_LOW_HP && !usedEmergencyHeal) {
            int heal = (int)(hero.getMaxHealth() * 0.2);
            hero.heal(heal);
            usedEmergencyHeal = true;
            System.out.println("  >> Witcher instincts kick in! Emergency heal for " + heal + " HP!");
        }
    }
}