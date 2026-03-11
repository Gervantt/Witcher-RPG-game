package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.character.Character;
import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.ability.igni.IgniBlast;
import main.java.com.narxoz.rpg.combat.ability.frost.AardFrostBlast;
import main.java.com.narxoz.rpg.combat.ability.yrden.YrdenTrap;

import java.util.ArrayList;
import java.util.List;

public class HeroCombatantAdapter implements Combatant {

    private final Character hero;
    private final int maxHealth;
    private final List<Ability> signs;
    private boolean potionUsed = false;

    public HeroCombatantAdapter(Character hero) {
        this.hero = hero;
        this.maxHealth = hero.getHealth();
        this.signs = new ArrayList<>();

        signs.add(new IgniBlast());
        signs.add(new AardFrostBlast());
        signs.add(new YrdenTrap());
    }

    public HeroCombatantAdapter(Character hero, List<Ability> customSigns) {
        this.hero = hero;
        this.maxHealth = hero.getHealth();
        this.signs = customSigns;
    }

    @Override
    public String getName() {
        return hero.getName();
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getCurrentHealth() {
        return hero.getHealth();
    }

    @Override
    public int getAttackPower() {
        return hero.getStrength();
    }

    @Override
    public int getMagicPower() {
        return hero.getMagic();
    }

    @Override
    public int getDefencePower() {
        return hero.getAgility() / 3;
    }

    @Override
    public int getAgilityValue() {
        return hero.getAgility();
    }

    @Override
    public void takeDamage(int amount) {
        int reduced = Math.max(0, amount - getDefencePower());
        int newHp = Math.max(0, hero.getHealth() - reduced);
        hero.setHealth(newHp);
    }

    @Override
    public void heal(int amount) {
        if (!hasPotionAvailable()) {
            return;
        }
        int newHp = Math.min(maxHealth, hero.getHealth() + amount);
        hero.setHealth(newHp);
        usePotionCharge();
    }

    @Override
    public boolean isAlive() {
        return hero.getHealth() > 0;
    }

    @Override
    public List<Ability> getAbilities() {
        return signs;
    }

    public void setCurrentHealth(int maxHealth) {
        this.hero.setHealth(maxHealth);
    }

    public void setAttackPower(int power) {
        this.hero.setStrength(power);
    }

    public void setMagicPower(int power) {
        this.hero.setMagic(power);
    }

    public void setAgilityValue(int value) {
        this.hero.setAgility(value);
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

    public Character getHero() {
        return hero;
    }

    public boolean hasPotionAvailable() {
        return !potionUsed;
    }

    public void usePotionCharge() {
        potionUsed = true;
    }

    @Override
    public String getStatusBar() {
        int hp = getCurrentHealth();
        int max = getMaxHealth();
        int bars = (int) ((double) hp / max * 20);
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" [");
        for (int i = 0; i < 20; i++) {
            sb.append(i < bars ? "#" : "-");
        }
        sb.append("] ").append(hp).append("/").append(max).append(" HP");
        return sb.toString();
    }
}