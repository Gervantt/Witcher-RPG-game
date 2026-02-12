package main.java.com.narxoz.rpg.factory;


import main.java.com.narxoz.rpg.character.Character;
import main.java.com.narxoz.rpg.character.Mage;

public class MageFactory implements CharacterFactory {

    @Override
    public Character createCharacter(String name) {
        return new Mage(name);
    }

}
