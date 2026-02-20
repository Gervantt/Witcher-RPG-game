package main.java.com.narxoz.rpg.enemy.ai;

public class DefensiveAI implements AIBehavior {

    @Override
    public String getBehaviorType() { return "Defensive"; }

    @Override
    public String describeAction(String enemyName) {
        return enemyName + " holds its ground, raising its defences and waiting for the perfect counter-attack moment.";
    }
}
