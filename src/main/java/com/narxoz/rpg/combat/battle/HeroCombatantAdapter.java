package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.character.Character;
import main.java.com.narxoz.rpg.combat.ability.Ability;
import main.java.com.narxoz.rpg.combat.ability.igni.IgniBlast;
import main.java.com.narxoz.rpg.combat.ability.frost.AardFrostBlast;
import main.java.com.narxoz.rpg.combat.ability.yrden.YrdenTrap;
import main.java.com.narxoz.rpg.combat.bridge.AreaSkill;
import main.java.com.narxoz.rpg.combat.bridge.FireEffect;
import main.java.com.narxoz.rpg.combat.bridge.IceEffect;

import java.util.ArrayList;
import java.util.List;

public class HeroCombatantAdapter implements Combatant {

    private final Character hero;
    private int maxHealth;
    private final List<Ability> signs;
    private boolean potionUsed = false;

    public HeroCombatantAdapter(Character hero) {
        this.hero = hero;
        this.maxHealth = hero.getHealth();
        this.signs = new ArrayList<>();
        signs.add(new IgniBlast());
        signs.add(new AardFrostBlast());
        signs.add(new YrdenTrap());
        signs.add(new AreaSkill("Igni Inferno", 35, new FireEffect()));
        signs.add(new AreaSkill("Blizzard", 30, new IceEffect()));
    }

    public HeroCombatantAdapter(Character hero, List<Ability> customSigns) {
        this.hero = hero;
        this.maxHealth = hero.getHealth();
        this.signs = customSigns != null ? customSigns : new ArrayList<>();
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

    /** Translates Character.getStrength() -> Combatant.getAttackPower() */
    @Override
    public int getAttackPower() {
        return hero.getStrength();
    }

    /** Translates Character.getMagic() -> Combatant.getMagicPower() */
    @Override
    public int getMagicPower() {
        return hero.getMagic();
    }

    /** Heroes have no explicit defence stat, so we derive it from agility */
    @Override
    public int getDefencePower() {
        return hero.getAgility() / 3;
    }

    @Override
    public int getAgilityValue() {
        return hero.getAgility();
    }

    /** Translates Combatant.takeDamage() -> Character.setHealth() */
    @Override
    public void takeDamage(int amount) {
        int reduced = Math.max(0, amount - getDefencePower());
        int newHp = Math.max(0, hero.getHealth() - reduced);
        hero.setHealth(newHp);
    }

    @Override
    public void heal(int amount) {
        int newHp = Math.min(maxHealth, hero.getHealth() + amount);
        hero.setHealth(newHp);
    }

    public void increaseMaxHealth(int amount) {
        maxHealth += amount;
        hero.setHealth(hero.getHealth() + amount);
    }

    public void setCurrentHealth(int hp) {
        hero.setHealth(hp);
    }

    @Override
    public boolean isAlive() {
        return hero.getHealth() > 0;
    }

    @Override
    public List<Ability> getAbilities() {
        return signs;
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

    /** Access to underlying hero for special ability use */
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