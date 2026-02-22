package main.java.com.narxoz.rpg.equipment.armor;

import main.java.com.narxoz.rpg.character.Character;

public class WolvenSet implements Armor {

    private int defence;
    private String description;
    private ArmorType type;

    public WolvenSet() {
        defence = 100;
        description = "Wolf School armor for hunters. Strong protection, easy movement, built for long fights.";
        type = ArmorType.MEDIEVAL;
    }
    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public String getArmorInfo() {
        return description;
    }

    @Override
    public String getArmorType() {
        return type.toString().toLowerCase();
    }

    @Override
    public void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    public String specialEnchantment(Character character) {
        int newStrength = character.getStrength() + 25;
        character.setStrength(newStrength);

        return "=== SPECIAL ENCHANT USED ===\n"
                + "Your armor got a special enchantment. From now on, you feel as light as a feather.\n"
                + "=== Strength INCREASED BY 25 ===\n"
                + "STRENGTH: " + newStrength;
    }
}
