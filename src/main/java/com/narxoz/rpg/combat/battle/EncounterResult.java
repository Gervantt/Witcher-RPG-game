package main.java.com.narxoz.rpg.combat.battle;

import java.util.ArrayList;
import java.util.List;

public class EncounterResult {

    private final Combatant winner;
    private final Combatant loser;
    private final int totalRounds;
    private final List<String> battleLog;

    public EncounterResult(Combatant winner, Combatant loser, int totalRounds, List<String> battleLog) {
        this.winner = winner;
        this.loser = loser;
        this.totalRounds = totalRounds;
        this.battleLog = battleLog != null ? battleLog : new ArrayList<>();
    }

    public Combatant getWinner() { return winner; }
    public Combatant getLoser() { return loser; }
    public int getTotalRounds() { return totalRounds; }
    public List<String> getBattleLog() { return battleLog; }

    public void printSummary() {
        System.out.println("\n====================================");
        System.out.println("        BATTLE SUMMARY");
        System.out.println("====================================");
        System.out.println("Winner: " + winner.getName() + " (" + winner.getCurrentHealth() + " HP remaining)");
        System.out.println("Defeated: " + loser.getName());
        System.out.println("Total Rounds: " + totalRounds);
        System.out.println("====================================");
    }
}