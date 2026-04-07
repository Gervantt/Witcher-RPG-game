package main.java.com.narxoz.rpg.combat.command;

import main.java.com.narxoz.rpg.combat.chain.DodgeHandler;

public class DefendCommand implements ActionCommand {

    private String heroName;
    private DodgeHandler dodgeHandler;
    private int dodgeBoost;

    public DefendCommand(String heroName, DodgeHandler dodgeHandler, int dodgeBoost) {
        this.heroName = heroName;
        this.dodgeHandler = dodgeHandler;
        this.dodgeBoost = dodgeBoost;
    }

    @Override
    public void execute() {
        dodgeHandler.modifyDodgeChance(dodgeBoost);
        System.out.println("  " + heroName + " takes a defensive stance! Dodge +" + dodgeBoost + "% (now " + dodgeHandler.getDodgeChance() + "%)");
    }

    @Override
    public void undo() {
        dodgeHandler.modifyDodgeChance(-dodgeBoost);
        System.out.println("  [UNDO] Dodge chance reduced by " + dodgeBoost + "%");
    }

    @Override
    public String getDescription() {
        return "Defend (dodge +" + dodgeBoost + "%)";
    }
}