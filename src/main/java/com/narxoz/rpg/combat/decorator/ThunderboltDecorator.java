package main.java.com.narxoz.rpg.combat.decorator;

public class ThunderboltDecorator extends ActionDecorator {

    public ThunderboltDecorator(AttackAction wrapped) {
        super(wrapped);
    }

    public int getDamage() { return (int)(wrapped.getDamage() * 1.4); }

    public String getDescription() { return wrapped.getDescription() + " + Thunderbolt"; }
}