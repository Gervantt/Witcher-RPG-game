package main.java.com.narxoz.rpg.combat.observer;

public class GameEvent {

    private final GameEventType type;
    private final String sourceName;
    private final String message;
    private final int value;

    public GameEvent(GameEventType type, String sourceName, String message, int value) {
        this.type = type;
        this.sourceName = sourceName;
        this.message = message;
        this.value = value;
    }

    public GameEvent(GameEventType type, String sourceName, String message) {
        this(type, sourceName, message, 0);
    }

    public GameEventType getType() { return type; }
    public String getSourceName() { return sourceName; }
    public String getMessage() { return message; }
    public int getValue() { return value; }
}