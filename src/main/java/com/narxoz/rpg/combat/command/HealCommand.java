package main.java.com.narxoz.rpg.combat.command;

import main.java.com.narxoz.rpg.combat.battle.Combatant;
import main.java.com.narxoz.rpg.combat.battle.HeroCombatantAdapter;

public class HealCommand implements ActionCommand {

    private Combatant target;
    private int healAmount;
    private int actualHealed;

    public HealCommand(Combatant target, int healAmount) {
        this.target = target;
        this.healAmount = healAmount;
        this.actualHealed = 0;
    }

    @Override
    public void execute() {
        if (target instanceof HeroCombatantAdapter) {
            HeroCombatantAdapter hero = (HeroCombatantAdapter) target;
            if (!hero.hasPotionAvailable()) {
                System.out.println("  No potions left! Heal skipped.");
                return;
            }
            hero.usePotionCharge();
        }
        int hpBefore = target.getCurrentHealth();
        target.heal(healAmount);
        actualHealed = target.getCurrentHealth() - hpBefore;
        System.out.println("  " + target.getName() + " heals for " + actualHealed + " HP!");
    }

    @Override
    public void undo() {
        target.takeDamage(actualHealed);
        System.out.println("  [UNDO] Removed " + actualHealed + " HP from " + target.getName());
    }

    @Override
    public String getDescription() {
        return "Heal " + target.getName() + " (" + healAmount + " HP)";
    }
}