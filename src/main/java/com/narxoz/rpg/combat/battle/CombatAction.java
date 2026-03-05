package main.java.com.narxoz.rpg.combat.battle;

public enum CombatAction {
    SWORD_ATTACK("Sword Attack", "Strike with your equipped weapon."),
    SIGN_CAST("Cast Sign", "Channel a Witcher sign or use an ability."),
    DEFEND("Defend", "Raise your guard, halving incoming damage this turn."),
    USE_POTION("Use Potion", "Drink a Swallow potion to restore health. One use per fight."),
    SPECIAL("Special Ability", "Unleash your class special ability.");

    private final String displayName;
    private final String description;

    CombatAction(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() { return displayName; }
    public String getDescription() { return description; }
}