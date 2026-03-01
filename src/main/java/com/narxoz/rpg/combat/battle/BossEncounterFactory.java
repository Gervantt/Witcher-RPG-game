package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.combat.ability.vampire.BloodFrenzy;
import main.java.com.narxoz.rpg.combat.ability.wildhunt.*;
import main.java.com.narxoz.rpg.combat.ability.yrden.PhantomNova;
import main.java.com.narxoz.rpg.enemy.BossEnemy;
import main.java.com.narxoz.rpg.enemy.builder.BossType;
import main.java.com.narxoz.rpg.enemy.builder.BossEnemyBuilder;
import main.java.com.narxoz.rpg.factory.enemiesfactory.WildHuntFactory;
import main.java.com.narxoz.rpg.factory.enemiesfactory.VampireFactory;
import main.java.com.narxoz.rpg.loot.HigherVampireLootTable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BossEncounterFactory {

    private static final WildHuntFactory wildHuntFactory = new WildHuntFactory();
    private static final VampireFactory vampireFactory = new VampireFactory();


    //  EREDIN - King of the Wild Hunt
    public static BossCombatantAdapter createEredin() {
        BossEnemy eredin = new BossEnemyBuilder()
                .withType(BossType.EREDIN)
                .withName("Eredin Breac Glas")
                .withLevel(50)
                .withHealth(800)
                .withDamage(55)
                .withDefence(25)
                .withAgility(50)
                .withAbilities(wildHuntFactory.createAbilities())
                .withLootTable(wildHuntFactory.createLootTable())
                .withAIBehavior(wildHuntFactory.createAIBehavior())
                .withLoreDescription("King of the Wild Hunt, commander of the Red Riders. "
                        + "Seeks to capture Ciri and harness her Elder Blood.")
                .addPhase("The Warrior King")
                .addPhase("Frost Commander")
                .addPhase("Desperate Fury")
                .build();

        List<BossPhase> phases = Arrays.asList(
                new BossPhase(eredin.getCurrentPhaseDescription(), 1.0,
                        Collections.emptyList(), 1.0, 1.0,
                        "Eredin draws his blade. 'You will not stop the Hunt, Witcher.'"),

                new BossPhase(eredin.getCurrentPhaseDescription(), 0.6,
                        Arrays.asList(new FrostNova()),
                        1.3, 0.8,
                        "Eredin slams his fist into the ground. Frost erupts around him!\n"
                                + "  'The White Frost will consume your world. You merely delay the inevitable.'"),

                new BossPhase(eredin.getCurrentPhaseDescription(), 0.25,
                        Arrays.asList(new WhiteFrostCataclysm()),
                        1.6, 0.5,
                        "Eredin tears open a portal. Raw energy of the White Frost bleeds through!\n"
                                + "  'ENOUGH! I will end you myself, even if this world burns!'")
        );

        return new BossCombatantAdapter(eredin, phases);
    }

    //  IMLERITH - General of the Wild Hunt
    public static BossCombatantAdapter createImlerith() {
        BossEnemy imlerith = new BossEnemyBuilder()
                .withType(BossType.IMLERITH)
                .withName("Imlerith")
                .withLevel(45)
                .withHealth(600)
                .withDamage(65)
                .withDefence(30)
                .withAgility(35)
                .withAbilities(wildHuntFactory.createAbilities())
                .withLootTable(wildHuntFactory.createLootTable())
                .withAIBehavior(wildHuntFactory.createAIBehavior())
                .withLoreDescription("General of the Wild Hunt and one of Eredin's most "
                        + "trusted lieutenants. A brutal warrior who wields a massive mace.")
                .addPhase("Shield Bearer")
                .addPhase("Berserker Rage")
                .build();

        List<BossPhase> phases = Arrays.asList(
                new BossPhase(imlerith.getCurrentPhaseDescription(), 1.0,
                        Collections.emptyList(), 1.0, 1.5,
                        "Imlerith raises his massive shield. 'You dare challenge a general of the Hunt?'"),

                new BossPhase(imlerith.getCurrentPhaseDescription(), 0.4,
                        Arrays.asList(new FrostNova(), new BlinkStrike()),
                        1.8, 0.6,
                        "Imlerith hurls his shield aside and grips his mace with both hands!\n"
                                + "  'NO MORE GAMES! I will crush you where you stand!'")
        );

        return new BossCombatantAdapter(imlerith, phases);
    }


    //  DETLAFF - Higher Vampire
    public static BossCombatantAdapter createDetlaff() {
        BossEnemy detlaff = new BossEnemyBuilder()
                .withType(BossType.DETLAFF)
                .withName("Detlaff van der Eretein")
                .withLevel(48)
                .withHealth(700)
                .withDamage(60)
                .withDefence(20)
                .withAgility(60)
                .withAbilities(vampireFactory.createAbilities())
                .withLootTable(new HigherVampireLootTable())
                .withAIBehavior(vampireFactory.createAIBehavior())
                .withLoreDescription("A Higher Vampire of immense power. Unlike lesser vampires, "
                        + "he cannot be truly killed by any mortal means.")
                .addPhase("The Gentleman")
                .addPhase("Beast Unleashed")
                .addPhase("True Vampire Form")
                .build();

        List<BossPhase> phases = Arrays.asList(
                new BossPhase("The Gentleman", 1.0,
                        Collections.emptyList(), 1.0, 1.0,
                        "Detlaff stands motionless. 'I gave you a chance to walk away, Witcher.'"),

                new BossPhase("Beast Unleashed", 0.6,
                        Arrays.asList(new BloodFrenzy()),
                        1.4, 0.7,
                        "Detlaff's form ripples and distorts. Claws extend from his fingers!\n"
                                + "  'You have no idea what you have unleashed!'"),

                new BossPhase("True Vampire Form", 0.25,
                        Arrays.asList(new PhantomNova(), new BloodFrenzy()),
                        1.7, 0.4,
                        "Detlaff transforms completely. Wings of blood erupt from his back!\n"
                                + "  A shriek echoes that shakes the very foundations of the chamber.")
        );

        return new BossCombatantAdapter(detlaff, phases);
    }
}