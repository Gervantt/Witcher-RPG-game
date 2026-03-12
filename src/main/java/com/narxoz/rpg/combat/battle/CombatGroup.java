package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.combat.ability.Ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombatGroup implements Combatant {

    private final String groupName;
    private final List<Combatant> members;

    public CombatGroup(String groupName, Combatant... members) {
        this.groupName = groupName;
        this.members = new ArrayList<>(Arrays.asList(members));
    }

    public List<Combatant> getAliveMembers() {
        List<Combatant> alive = new ArrayList<>();
        for (Combatant m : members) if (m.isAlive()) alive.add(m);
        return alive;
    }

    public List<Combatant> getAllMembers() { return members; }

    @Override
    public String getName() { return groupName; }

    @Override
    public int getMaxHealth() {
        int total = 0;
        for (Combatant m : members) total += m.getMaxHealth();
        return total;
    }

    @Override
    public int getCurrentHealth() {
        int total = 0;
        for (Combatant m : members) total += m.getCurrentHealth();
        return total;
    }

    @Override
    public int getAttackPower() {
        int total = 0;
        for (Combatant m : getAliveMembers()) total += m.getAttackPower();
        return total;
    }

    @Override
    public int getMagicPower() {
        int total = 0;
        for (Combatant m : getAliveMembers()) total += m.getMagicPower();
        return total;
    }

    @Override
    public int getDefencePower() {
        int total = 0;
        for (Combatant m : getAliveMembers()) total += m.getDefencePower();
        return total;
    }

    @Override
    public int getAgilityValue() {
        List<Combatant> alive = getAliveMembers();
        if (alive.isEmpty()) return 0;
        int total = 0;
        for (Combatant m : alive) total += m.getAgilityValue();
        return total / alive.size();
    }

    @Override
    public void takeDamage(int amount) {
        List<Combatant> alive = getAliveMembers();
        if (alive.isEmpty()) return;
        alive.get(0).takeDamage(amount);
    }

    public void takeAreaDamage(int amount) {
        for (Combatant m : getAliveMembers()) m.takeDamage(amount);
    }

    @Override
    public void heal(int amount) {
        for (Combatant m : getAliveMembers()) m.heal(amount / getAliveMembers().size());
    }

    @Override
    public boolean isAlive() {
        for (Combatant m : members) if (m.isAlive()) return true;
        return false;
    }

    @Override
    public List<Ability> getAbilities() {
        List<Combatant> alive = getAliveMembers();
        if (alive.isEmpty()) return new ArrayList<>();
        return alive.get(0).getAbilities();
    }

    @Override
    public boolean isBoss() { return false; }

    @Override
    public String getPhaseName() { return ""; }

    @Override
    public boolean checkPhaseTransition() { return false; }

    @Override
    public String getStatusBar() {
        StringBuilder sb = new StringBuilder();
        sb.append(groupName).append(" (").append(getAliveMembers().size()).append("/").append(members.size()).append(" alive)\n");
        for (Combatant m : members) {
            String status = m.isAlive() ? m.getStatusBar() : m.getName() + " [DEAD]";
            sb.append("    ").append(status).append("\n");
        }
        return sb.toString().trim();
    }
}