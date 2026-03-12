package main.java.com.narxoz.rpg.combat.bridge;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public abstract class Skill implements Ability {

    protected String name;
    protected int baseDamage;
    protected EffectImplementor effect;

    public Skill(String name, int baseDamage, EffectImplementor effect) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.effect = effect;
    }

    @Override
    public String getName() { return name + " (" + effect.getEffectName() + ")"; }

    @Override
    public int getDamage() { return effect.applyEffect(baseDamage); }

    @Override
    public String getDescription() { return effect.getDescription(); }

    @Override
    public Ability clone(){
        return this;
    }

    public EffectImplementor getEffect() { return effect; }

    public abstract boolean isArea();
}