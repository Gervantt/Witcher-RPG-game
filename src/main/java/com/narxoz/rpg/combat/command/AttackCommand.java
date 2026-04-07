package main.java.com.narxoz.rpg.combat.command;

import main.java.com.narxoz.rpg.combat.battle.Combatant;

public class AttackCommand implements ActionCommand {

    private Combatant attacker;
    private Combatant target;
    private int attackPower;
    private int damageDealt;

    public AttackCommand(Combatant attacker, Combatant target, int attackPower) {
        this.attacker = attacker;
        this.target = target;
        this.attackPower = attackPower;
        this.damageDealt = 0;
    }

    @Override
    public void execute() {
        int hpBefore = target.getCurrentHealth();
        target.takeDamage(attackPower);
        damageDealt = hpBefore - target.getCurrentHealth();
        System.out.println("  " + attacker.getName() + " attacks " + target.getName() + " for " + damageDealt + " damage!");
    }

    @Override
    public void undo() {
        target.heal(damageDealt);
        System.out.println("  [UNDO] Restored " + damageDealt + " HP to " + target.getName());
    }

    @Override
    public String getDescription() {
        return "Attack " + target.getName() + " (" + attackPower + " power)";
    }
}