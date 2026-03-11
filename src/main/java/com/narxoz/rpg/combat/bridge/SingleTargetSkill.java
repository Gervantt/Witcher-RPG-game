package main.java.com.narxoz.rpg.combat.bridge;

import main.java.com.narxoz.rpg.combat.ability.Ability;

public class SingleTargetSkill extends Skill {

    public SingleTargetSkill(String name, int baseDamage, EffectImplementor effect) {
        super(name, baseDamage, effect);
    }

    @Override
    public boolean isArea() { return false; }

    @Override
    public Ability clone() {
        return  new SingleTargetSkill(getName(), baseDamage, effect);
    }
}
