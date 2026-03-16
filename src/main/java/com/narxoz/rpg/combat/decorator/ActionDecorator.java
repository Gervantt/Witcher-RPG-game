package main.java.com.narxoz.rpg.combat.decorator;

public abstract class ActionDecorator implements AttackAction {

    protected AttackAction wrapped;

    public ActionDecorator(AttackAction wrapped) {
        this.wrapped = wrapped;
    }
}