package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class BossCombatantAdapter implements Combatant {

    private final Enemy enemy;
    private int currentHealth;
    private final int maxHealth;
    private final List<BossPhase> phases;
    private int currentPhaseIndex;
    private String lastPhaseTransitionMessage;

    public BossCombatantAdapter(Enemy enemy, List<BossPhase> phases) {
        this.enemy = enemy;
        this.maxHealth = enemy.getHealth();
        this.currentHealth = enemy.getHealth();
        this.phases = phases;
        this.currentPhaseIndex = 0;
        this.lastPhaseTransitionMessage = null;
    }

    @Override
    public String getName() {
        return enemy.getName();
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public int getAttackPower() {
        return (int) (enemy.getDamage() * getCurrentPhase().getDamageMultiplier());
    }

    @Override
    public int getMagicPower() {
        return (int) (enemy.getDamage() * 0.7 * getCurrentPhase().getDamageMultiplier());
    }

    @Override
    public int getDefencePower() {
        return (int) (enemy.getDefence() * getCurrentPhase().getDamageMultiplier());
    }

    @Override
    public int getAgilityValue() {
        return enemy.getAgility();
    }

    @Override
    public void takeDamage(int amount) {
        int reduced = Math.max(1, amount - getDefencePower());
        currentHealth = Math.max(0, currentHealth - reduced);
    }

    @Override
    public void heal(int amount) {
        currentHealth = Math.min(maxHealth, currentHealth + amount);
    }

    @Override
    public boolean isAlive() {
        return currentHealth > 0;
    }

    @Override
    public List<Ability> getAbilities() {
        List<Ability> all = new ArrayList<>(enemy.getAbilities());
        BossPhase phase = getCurrentPhase();
        if (phase != null && phase.getPhaseAbilities() != null) {
            all.addAll(phase.getPhaseAbilities());
        }
        return all;
    }

    @Override
    public boolean isBoss() {
        return true;
    }

    @Override
    public String getPhaseName() {
        BossPhase phase = getCurrentPhase();
        return phase.getPhaseName();
    }

    @Override
    public boolean checkPhaseTransition() {
        if (phases.isEmpty() || !isAlive()) return false;

        double hpPercent = (double) currentHealth / maxHealth;
        int nextPhase = currentPhaseIndex + 1;

        if (nextPhase < phases.size()) {
            BossPhase next = phases.get(nextPhase);
            if (hpPercent <= next.getHpThreshold()) {
                currentPhaseIndex = nextPhase;
                lastPhaseTransitionMessage = next.getEntranceLine();
                return true;
            }
        }
        return false;
    }

    public String consumePhaseTransitionMessage() {
        String msg = lastPhaseTransitionMessage;
        lastPhaseTransitionMessage = null;
        return msg;
    }

    public BossPhase getCurrentPhase() {
        if (phases.isEmpty() || currentPhaseIndex >= phases.size()) return null;
        return phases.get(currentPhaseIndex);
    }

    public int getCurrentPhaseIndex() {
        return currentPhaseIndex;
    }

    public int getTotalPhases() {
        return phases.size();
    }

    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public String getStatusBar() {
        int hp = getCurrentHealth();
        int max = getMaxHealth();
        int bars = (int) ((double) hp / max * 20);
        StringBuilder sb = new StringBuilder();
        sb.append("BOSS: ").append(getName());
        sb.append(" (Lv.").append(enemy.getLevel()).append(")");
        sb.append(" [Phase: ").append(getPhaseName()).append("] [");
        for (int i = 0; i < 20; i++) {
            sb.append(i < bars ? "#" : "-");
        }
        sb.append("] ").append(hp).append("/").append(max).append(" HP");
        return sb.toString();
    }
}