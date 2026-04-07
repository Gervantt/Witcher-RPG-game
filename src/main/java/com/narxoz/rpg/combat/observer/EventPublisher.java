package main.java.com.narxoz.rpg.combat.observer;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {

    private final List<GameObserver> observers = new ArrayList<>();

    public void subscribe(GameObserver observer) { observers.add(observer); }

    public void unsubscribe(GameObserver observer) { observers.remove(observer); }

    public void fireEvent(GameEvent event) {
        for (GameObserver observer : observers) observer.onEvent(event);
    }
}