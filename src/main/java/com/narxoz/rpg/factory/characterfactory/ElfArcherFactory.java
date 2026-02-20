package main.java.com.narxoz.rpg.factory.characterfactory;

import main.java.com.narxoz.rpg.character.ElfArcher;
import main.java.com.narxoz.rpg.character.Character;

public class ElfArcherFactory implements CharacterFactory {

    @Override
    public Character createCharacter(String name) {
        return new ElfArcher(name);
    }

}
