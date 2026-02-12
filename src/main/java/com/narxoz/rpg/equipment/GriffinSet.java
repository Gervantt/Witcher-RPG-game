package main.java.com.narxoz.rpg.equipment;

import main.java.com.narxoz.rpg.character.Character;

public class GriffinSet implements Armor {

    private int defence;
    private String description;
    private ArmorType type;

    public GriffinSet(){
        defence = 50;
        description = "Runed mage armor. Gives better control and makes your spells hit harder.";
        type = ArmorType.MAGICAL;
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
        int newMagic = character.getMagic() + 25;
        character.setMagic(newMagic);

        return "=== SPECIAL ENCHANT USED ===\n"
                + "Your armor got a special enchantment. From now on, you feel the growth in thy magic powers.\n"
                + "=== MAGIC INCREASED BY 25 ===\n"
                + "MAGIC: " + newMagic;
    }
}
