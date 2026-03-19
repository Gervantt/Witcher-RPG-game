package main.java.com.narxoz.rpg;

import main.java.com.narxoz.rpg.character.Character;
import main.java.com.narxoz.rpg.combat.battle.*;
import main.java.com.narxoz.rpg.combat.bridge.*;
import main.java.com.narxoz.rpg.combat.decorator.*;
import main.java.com.narxoz.rpg.combat.facade.DungeonFacade;
import main.java.com.narxoz.rpg.equipment.armor.Armor;
import main.java.com.narxoz.rpg.equipment.weapon.Weapon;
import main.java.com.narxoz.rpg.factory.characterfactory.CharacterFactory;
import main.java.com.narxoz.rpg.factory.characterfactory.WitcherFactory;
import main.java.com.narxoz.rpg.factory.equipmentfactory.EquipmentFactory;
import main.java.com.narxoz.rpg.factory.equipmentfactory.MedievalEquipmentFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("  SINGLETON PATTERN");
        System.out.println("====================================");

        BattleEngine engine1 = BattleEngine.getInstance();
        BattleEngine engine2 = BattleEngine.getInstance();
        System.out.println("Same instance? " + (engine1 == engine2));
        System.out.println();

        System.out.println("====================================");
        System.out.println("  ADAPTER PATTERN");
        System.out.println("====================================");

        CharacterFactory characterFactory = new WitcherFactory();
        Character geralt = characterFactory.createCharacter("Geralt of Rivia");

        EquipmentFactory equipmentFactory = new MedievalEquipmentFactory();
        Weapon weapon = equipmentFactory.createWeapon();
        Armor armor = equipmentFactory.createArmor();
        geralt.equipArmor(armor);
        geralt.equipWeapon(weapon);
        System.out.println();

        HeroCombatantAdapter heroCombatant = new HeroCombatantAdapter(geralt);
        System.out.println("Hero adapted: " + heroCombatant.getStatusBar());
        System.out.println();

        System.out.println("====================================");
        System.out.println("  COMPOSITE PATTERN");
        System.out.println("====================================");

        CombatGroup nekkerPack = new CombatGroup("Nekker Gang",
                EnemyEncounterFactory.createNekkerGang().toArray(new Combatant[0]));
        System.out.println("Group as single Combatant:");
        System.out.println("  Name: " + nekkerPack.getName());
        System.out.println("  Total attack: " + nekkerPack.getAttackPower());
        System.out.println("  Alive: " + nekkerPack.getAliveMembers().size() + "/" + nekkerPack.getAllMembers().size());
        System.out.println("  isAlive: " + nekkerPack.isAlive());
        System.out.println();

        System.out.println("====================================");
        System.out.println("  BRIDGE PATTERN");
        System.out.println("====================================");

        Skill fireSingle = new SingleTargetSkill("Fire Bolt", 40, new FireEffect());
        Skill iceArea = new AreaSkill("Blizzard", 30, new IceEffect());
        Skill shadowSingle = new SingleTargetSkill("Shadow Strike", 50, new ShadowEffect());
        Skill physicalArea = new AreaSkill("Earthquake", 25, new PhysicalEffect());

        System.out.println("Same skill type, different effects:");
        System.out.println("  " + fireSingle.getName() + " -> " + fireSingle.getDamage() + " dmg");
        System.out.println("  " + shadowSingle.getName() + " -> " + shadowSingle.getDamage() + " dmg");
        System.out.println("Same effect, different skill types:");
        System.out.println("  " + iceArea.getName() + " (AOE: " + iceArea.isArea() + ") -> " + iceArea.getDamage() + " dmg");
        System.out.println("  " + physicalArea.getName() + " (AOE: " + physicalArea.isArea() + ") -> " + physicalArea.getDamage() + " dmg");
        System.out.println();

        System.out.println("====================================");
        System.out.println("  DECORATOR PATTERN");
        System.out.println("====================================");

        AttackAction base = new BasicAttack(heroCombatant.getAttackPower());
        System.out.println("Base: " + base.getDescription() + " -> " + base.getDamage() + " dmg");

        AttackAction oiled = new SpecterOilDecorator(base);
        System.out.println("+ Oil: " + oiled.getDescription() + " -> " + oiled.getDamage() + " dmg");

        AttackAction runed = new SvarogRunestoneDecorator(oiled);
        System.out.println("+ Rune: " + runed.getDescription() + " -> " + runed.getDamage() + " dmg");

        AttackAction buffed = new ThunderboltDecorator(runed);
        System.out.println("+ Potion: " + buffed.getDescription() + " -> " + buffed.getDamage() + " dmg");

        AttackAction heavy = new HeavyAttack(heroCombatant.getAttackPower());
        System.out.println("Heavy: " + heavy.getDescription() + " -> " + heavy.getDamage() + " dmg");

        AttackAction crossbow = new CrossbowShot(heroCombatant.getAttackPower());
        System.out.println("Crossbow: " + crossbow.getDescription() + " -> " + crossbow.getDamage() + " dmg");
        System.out.println();

        System.out.println("====================================");
        System.out.println("  FACADE PATTERN - DUNGEON ADVENTURE");
        System.out.println("====================================");
        System.out.println();

        BattleEngine.getInstance().setRandomSeed(System.currentTimeMillis());
        DungeonFacade dungeon = new DungeonFacade();
        dungeon.runAdventure(heroCombatant, scanner);

        scanner.close();
    }
}