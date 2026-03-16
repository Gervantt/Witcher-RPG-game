package main.java.com.narxoz.rpg.combat.decorator;

public class SvarogRunestoneDecorator extends ActionDecorator {

    public SvarogRunestoneDecorator(AttackAction wrapped) {
        super(wrapped);
    }

    public int getDamage() { return (int)(wrapped.getDamage() * 1.3); }

    public String getDescription() { return wrapped.getDescription() + " + Svarog Runestone"; }
}