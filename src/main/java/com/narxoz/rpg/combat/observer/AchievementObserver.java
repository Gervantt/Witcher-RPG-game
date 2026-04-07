package main.java.com.narxoz.rpg.combat.observer;

import java.util.ArrayList;
import java.util.List;

public class AchievementObserver implements GameObserver {

    private int attackCount = 0;
    private int dodgeCount = 0;
    private int critCount = 0;
    private final List<String> unlocked = new ArrayList<>();

    @Override
    public void onEvent(GameEvent event) {
        switch (event.getType()) {
            case ATTACK_LANDED:
                attackCount++;
                if (attackCount == 5 && !unlocked.contains("Relentless"))  unlock("Relentless - Landed 5 attacks");
                if (attackCount == 15 && !unlocked.contains("Butcher")) unlock("Butcher of Blaviken - Landed 15 attacks");
                break;
            case DODGE_SUCCESS:
                dodgeCount++;
                if (dodgeCount == 3 && !unlocked.contains("Dancer")) unlock("Dancer of Shadows - Dodged 3 times");
                break;
            case CRITICAL_HIT:
                critCount++;
                if (critCount == 2 && !unlocked.contains("Precise")) unlock("Precise Strike - Landed 2 crits");
                break;
            case ENEMY_DEFEATED:
                unlock("Monster Slayer - Defeated " + event.getSourceName());
                break;
            case BOSS_PHASE_CHANGE:
                if (event.getValue() <= 25) unlock("Cornered Beast - Pushed boss to final phase");
                break;
        }
    }

    private void unlock(String achievement) {
        unlocked.add(achievement);
        System.out.println("  ** ACHIEVEMENT UNLOCKED: " + achievement + " **");
    }

    public List<String> getUnlocked() { return unlocked; }

    public void printAchievements() {
        System.out.println("\n  === ACHIEVEMENTS ===");
        if (unlocked.isEmpty()) System.out.println("  None yet.");
        for (String a : unlocked) System.out.println("  * " + a);
    }
}