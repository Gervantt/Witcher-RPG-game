package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.enemy.Enemy;

import java.util.List;

public class EnemyCombatantAdapter implements Combatant {

    private final Enemy enemy;
    private int currentHealth;
    private final int maxHealth;

    public EnemyCombatantAdapter(Enemy enemy) {
        this.enemy = enemy;
        this.maxHealth = enemy.getHealth();
        this.currentHealth = enemy.getHealth();
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
        return enemy.getDamage();
    }

    @Override
    public int getMagicPower() {
        return 0;
    }

    @Override
    public int getDefencePower() {
        return enemy.getDefence();
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
        return enemy.getAbilities();
    }

    @Override
    public boolean isBoss() {
        return false;
    }

    @Override
    public String getPhaseName() {
        return "";
    }

    @Override
    public boolean checkPhaseTransition() {
        return false;
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
        sb.append(getName()).append(" (Lv.").append(enemy.getLevel()).append(") [");
        for (int i = 0; i < 20; i++) {
            sb.append(i < bars ? "#" : "-");
        }
        sb.append("] ").append(hp).append("/").append(max).append(" HP");
        return sb.toString();
    }
}