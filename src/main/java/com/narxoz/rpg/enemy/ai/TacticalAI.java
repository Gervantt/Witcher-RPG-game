package main.java.com.narxoz.rpg.enemy.ai;

public class TacticalAI implements AIBehavior {

    @Override
    public String getBehaviorType() { return "Tactical"; }

    @Override
    public String describeAction(String enemyName) {
        return enemyName + " moves through the shadows, studying weaknesses and striking only when the odds are in its favour.";
    }
}
