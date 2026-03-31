package main.java.com.narxoz.rpg.combat.command;

import java.util.ArrayList;
import java.util.List;

public class ActionQueue {

    private final List<ActionCommand> queue = new ArrayList<>();

    public void enqueue(ActionCommand command) {
        queue.add(command);
        System.out.println("  Queued: " + command.getDescription());
    }

    public void undoLast() {
        if (queue.isEmpty()) {
            System.out.println("  Queue is empty, nothing to undo.");
            return;
        }
        ActionCommand removed = queue.remove(queue.size() - 1);
        System.out.println("  Removed from queue: " + removed.getDescription());
    }

    public void executeAll() {
        for (ActionCommand cmd : queue) {
            cmd.execute();
        }
        queue.clear();
    }

    public List<String> getCommandDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (ActionCommand cmd : queue) {
            descriptions.add(cmd.getDescription());
        }
        return descriptions;
    }

    public int size() { return queue.size(); }

    public boolean isEmpty() { return queue.isEmpty(); }
}