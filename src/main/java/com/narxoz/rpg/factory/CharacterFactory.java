package main.java.com.narxoz.rpg.factory;

import main.java.com.narxoz.rpg.character.Character;

public interface CharacterFactory {
    Character createCharacter(String name);
}

