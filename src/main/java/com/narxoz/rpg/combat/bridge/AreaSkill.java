package main.java.com.narxoz.rpg.combat.bridge;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class AreaSkill extends Skill {

    public AreaSkill(String name, int baseDamage, EffectImplementor effect) {
        super(name, baseDamage, effect);
    }

    @Override
    public boolean isArea() { return true; }

    @Override
    public Ability clone() {
        return new AreaSkill(getName(), baseDamage, effect);
    }
    
}