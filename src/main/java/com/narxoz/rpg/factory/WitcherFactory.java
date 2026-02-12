package main.java.com.narxoz.rpg.factory;

import main.java.com.narxoz.rpg.character.Character;
import main.java.com.narxoz.rpg.character.Witcher;

public class WitcherFactory implements CharacterFactory {

    @Override
    public Character createCharacter(String name) {
        return new Witcher(name);
    }

}
