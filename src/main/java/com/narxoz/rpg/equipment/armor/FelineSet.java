package main.java.com.narxoz.rpg.equipment.armor;
import main.java.com.narxoz.rpg.character.Character;

public class FelineSet implements Armor {

    private int defence;
    private String description;
    private ArmorType type;

    public FelineSet() {
        defence = 75;
        description = "Light armor for speed. Helps you dodge, move quietly, and strike first.";
        type = ArmorType.RANGER;
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
        int newAgility = character.getAgility() + 25;
        character.setAgility(newAgility);

        return "=== SPECIAL ENCHANT USED ===\n"
                + "Your armor got a special enchantment. From now on, you feel as light as a feather.\n"
                + "=== AGILITY INCREASED BY 25 ===\n"
                + "AGILITY: " + newAgility;
    }
}
