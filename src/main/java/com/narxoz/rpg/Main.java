package main.java.com.narxoz.rpg;

import main.java.com.narxoz.rpg.character.Character;
import main.java.com.narxoz.rpg.combat.battle.*;
import main.java.com.narxoz.rpg.equipment.armor.Armor;
import main.java.com.narxoz.rpg.equipment.weapon.Weapon;
import main.java.com.narxoz.rpg.factory.characterfactory.CharacterFactory;
import main.java.com.narxoz.rpg.factory.characterfactory.ElfArcherFactory;
import main.java.com.narxoz.rpg.factory.characterfactory.MageFactory;
import main.java.com.narxoz.rpg.factory.characterfactory.WitcherFactory;
import main.java.com.narxoz.rpg.factory.equipmentfactory.EquipmentFactory;
import main.java.com.narxoz.rpg.factory.equipmentfactory.MagicalEquipmentFactory;
import main.java.com.narxoz.rpg.factory.equipmentfactory.MedievalEquipmentFactory;
import main.java.com.narxoz.rpg.factory.equipmentfactory.RangerEquipmentFactory;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("  SINGLETON PATTERN DEMONSTRATION");
        System.out.println("====================================");

        BattleEngine engine1 = BattleEngine.getInstance();
        BattleEngine engine2 = BattleEngine.getInstance();

        System.out.println("Engine instance 1: " + engine1.hashCode());
        System.out.println("Engine instance 2: " + engine2.hashCode());
        System.out.println("Same instance? " + (engine1 == engine2));
        System.out.println();

        engine1.setRandomSeed(42L);

        System.out.println("====================================");
        System.out.println("  ADAPTER PATTERN DEMONSTRATION");
        System.out.println("====================================");

        CharacterFactory characterFactory = new WitcherFactory();
        Character geralt = characterFactory.createCharacter("Geralt of Rivia");

        EquipmentFactory equipmentFactory = new MedievalEquipmentFactory();
        Weapon weapon = equipmentFactory.createWeapon();
        Armor armor = equipmentFactory.createArmor();
        geralt.equipArmor(armor);
        geralt.equipWeapon(weapon);

        CharacterFactory characterFactory2 = new MageFactory();
        Character mage = characterFactory2.createCharacter("Yennifer");
        EquipmentFactory equipmentFactory2 = new MagicalEquipmentFactory();
        mage.equipWeapon(equipmentFactory2.createWeapon());
        mage.equipArmor(equipmentFactory2.createArmor());

        CharacterFactory characterFactory3 = new ElfArcherFactory();
        Character elf = characterFactory3.createCharacter("Avallac'h");
        EquipmentFactory equipmentFactory3 = new RangerEquipmentFactory();
        elf.equipArmor(equipmentFactory3.createArmor());
        elf.equipWeapon(equipmentFactory3.createWeapon());

        System.out.println();
        HeroCombatantAdapter heroCombatant = new HeroCombatantAdapter(geralt);

        BattleEngine engine = BattleEngine.getInstance();
        engine.setRandomSeed(System.currentTimeMillis());
        System.out.println("====================================");
        System.out.println("  THE WITCHER  ");
        System.out.println("====================================");
        System.out.println();
        System.out.println("Choose your character for the rest of the game: ");
        System.out.println("  [1] Gervant of Rivia - The Witcher");
        System.out.println("  [2] Yennifer - A Mage");
        System.out.println("  [3] Avallac'h - An Elf Archer");
        System.out.print("  > ");
        int chooseCharacter = scanner.nextInt();

        scanner.nextLine();
        System.out.println();
        switch (chooseCharacter) {
            case 1:
                heroCombatant = new HeroCombatantAdapter(geralt);
                break;
            case 2:
                heroCombatant = new HeroCombatantAdapter(mage);
                break;
            case 3:
                heroCombatant = new HeroCombatantAdapter(elf);
                break;
            default:
                System.out.println("You have entered an invalid character!");
                break;
        }

        while (true) {

            System.out.println("====================================");
            System.out.println("  THE WITCHER - BATTLE SYSTEM");
            System.out.println("====================================");
            System.out.println();
            System.out.println("Choose your encounter:");
            System.out.println("  [1] Ghoul Pack - Fight a Pack of Ghouls");
            System.out.println("  [2] Katakan - Lower Vampire");
            System.out.println("  [3] Vampire Nest - Fight 2 Lower Vampires");
            System.out.println("  [4] Drowner Pack - Fight a Pack of Drowners");
            System.out.println("  [5] Graveyard Haunt - Slaughter ghosts of Graveyard");
            System.out.println("  [6] Imlerith - Wild Hunt General (2-phase boss)");
            System.out.println("  [7] Detlaff - Higher Vampire (3-phase boss)");
            System.out.println("  [8] Eredin - King of the Wild Hunt (3-phase boss)");
            System.out.println("  [9] Reset - Reset Your Character");
            System.out.println("  [10] Exit - Exit the game");
            System.out.print("  > ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1: {
                    List<Combatant> ghouls = EnemyEncounterFactory.createGhoulPack();
                    for(int i = 0; i < ghouls.size(); i++) {
                        engine.runEncounter(heroCombatant, ghouls.get(i), scanner);
                    }
                    break;
                }
                case 2: {
                    EnemyCombatantAdapter vampire = EnemyEncounterFactory.createKatakan();
                    engine.runEncounter(heroCombatant, vampire, scanner);
                    break;
                }
                case 3: {
                    List<Combatant> vampires = EnemyEncounterFactory.createVampireNest();
                    for(int i = 0; i < vampires.size(); i++) {
                        engine.runEncounter(heroCombatant, vampires.get(i), scanner);
                    }
                    break;
                }
                case 4: {
                    List<Combatant> drowners = EnemyEncounterFactory.createDrownerPack();
                    for(int i = 0; i < drowners.size(); i++) {
                        engine.runEncounter(heroCombatant, drowners.get(i), scanner);
                    }
                }
                case 5:
                    List<Combatant> ghosts = EnemyEncounterFactory.createGraveyardHaunt();
                    for(int i = 0; i < ghosts.size(); i++) {
                        engine.runEncounter(heroCombatant, ghosts.get(i), scanner);
                    }
                case 6: {
                    BossCombatantAdapter imlerith = BossEncounterFactory.createImlerith();
                    engine.runEncounter(heroCombatant, imlerith, scanner);
                    break;
                }
                case 7: {
                    BossCombatantAdapter detlaff = BossEncounterFactory.createDetlaff();
                    engine.runEncounter(heroCombatant, detlaff, scanner);
                    break;
                }
                case 8: {
                    BossCombatantAdapter eredin = BossEncounterFactory.createEredin();
                    engine.runEncounter(heroCombatant, eredin, scanner);
                    break;
                }
                case 9: {
                    engine.reset(heroCombatant);
                    heroCombatant = new HeroCombatantAdapter(geralt);
                    System.out.println("Battle engine reset. Combat log cleared, hero restored.");
                    System.out.println();
                    break;
                }
                case 10: {
                    System.out.println(heroCombatant.getName() +" walks away...");
                    scanner.close();
                    return;
                }
                default: {
                    System.out.println("Invalid choice, try again.");
                    break;
                }
            }
        }
    }
}