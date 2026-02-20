package main.java.com.narxoz.rpg.enemy.ai;

public class AggressiveAI implements AIBehavior {

    @Override
    public String getBehaviorType() { return "Aggressive"; }

    @Override
    public String describeAction(String enemyName) {
        return enemyName + " charges forward relentlessly, targeting the nearest enemy with its most powerful attacks!";
    }
}
