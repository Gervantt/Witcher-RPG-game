package main.java.com.narxoz.rpg.combat.command;

public interface ActionCommand {
    void execute();
    void undo();
    String getDescription();
}