package main.java.com.narxoz.rpg.combat.decorator;

public class SpecterOilDecorator extends ActionDecorator {

    public SpecterOilDecorator(AttackAction wrapped) {
        super(wrapped);
    }

    public int getDamage() { return wrapped.getDamage() + 20; }

    public String getDescription() { return wrapped.getDescription() + " + Specter Oil"; }
}