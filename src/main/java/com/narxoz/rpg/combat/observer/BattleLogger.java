package main.java.com.narxoz.rpg.combat.observer;

import java.util.ArrayList;
import java.util.List;

public class BattleLogger implements GameObserver {

    private final List<String> log = new ArrayList<>();

    @Override
    public void onEvent(GameEvent event) {
        String entry = "[" + event.getType() + "] " + event.getMessage();
        log.add(entry);
        System.out.println("  LOG: " + entry);
    }

    public List<String> getLog() { return log; }

    public void printFullLog() {
        System.out.println("\n  === BATTLE LOG ===");
        for (String entry : log) System.out.println("  " + entry);
    }
}