package main.java.com.narxoz.rpg.combat.observer;

import main.java.com.narxoz.rpg.combat.strategy.*;

public class BossStrategyObserver implements GameObserver {

    private CombatStrategy currentStrategy;

    public BossStrategyObserver() {
        this.currentStrategy = new DefensiveStrategy();
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() != GameEventType.BOSS_PHASE_CHANGE) return;

        int hpPercent = event.getValue();

        if (hpPercent <= 25) {
            currentStrategy = new BerserkStrategy();
            System.out.println("  >> Boss enters BERSERK mode! Damage x2!");
        } else if (hpPercent <= 60) {
            currentStrategy = new AggressiveStrategy();
            System.out.println("  >> Boss switches to AGGRESSIVE! Damage x1.5!");
        } else {
            currentStrategy = new DefensiveStrategy();
            System.out.println("  >> Boss uses DEFENSIVE strategy.");
        }
    }

    public CombatStrategy getCurrentStrategy() { return currentStrategy; }
}