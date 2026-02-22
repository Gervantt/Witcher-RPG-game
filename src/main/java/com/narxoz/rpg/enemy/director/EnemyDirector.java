package main.java.com.narxoz.rpg.enemy.director;

import main.java.com.narxoz.rpg.combat.loot.VampireLootTable;
import main.java.com.narxoz.rpg.combat.loot.WildHuntLootTable;
import main.java.com.narxoz.rpg.enemy.Enemy;
import main.java.com.narxoz.rpg.enemy.builder.BasicEnemyBuilder;
import main.java.com.narxoz.rpg.enemy.builder.BossEnemyBuilder;
import main.java.com.narxoz.rpg.enemy.builder.BossType;
import main.java.com.narxoz.rpg.enemy.builder.EnemyType;
import main.java.com.narxoz.rpg.factory.enemiesfactory.UndeadFactory;
import main.java.com.narxoz.rpg.factory.enemiesfactory.VampireFactory;
import main.java.com.narxoz.rpg.factory.enemiesfactory.WildHuntFactory;

public class EnemyDirector {

    private EnemyDirector() {}

    public static Enemy createMinion() {
        return new BasicEnemyBuilder()
                .withType(EnemyType.NEKKER)
                .withName("Nekker Warrior")
                .withLevel(3)
                .withHealth(80)
                .withDamage(18)
                .withDefence(5)
                .withAgility(30)
                .withFactory(new UndeadFactory())
                .build();
    }

    public static Enemy createElite() {
        return new BasicEnemyBuilder()
                .withType(EnemyType.WRAITH)
                .withName("Nightwraith")
                .withLevel(15)
                .withHealth(350)
                .withDamage(65)
                .withDefence(30)
                .withAgility(60)
                .withFactory(new UndeadFactory())
                .build();
    }

    public static Enemy createMiniBoss() {
        return new BossEnemyBuilder()
                .withType(BossType.IMLERITH)
                .withName("Imlerith")
                .withLevel(25)
                .withHealth(2000)
                .withDamage(140)
                .withDefence(80)
                .withAgility(45)
                .withFactory(new WildHuntFactory())
                .withLootTable(new WildHuntLootTable())
                .withLoreDescription("Wild Hunt general who tortured Vesemir. Fought at Bald Mountain wielding a frost-enchanted mace and impenetrable shield.")
                .addPhase("Shield & Mace — defensive offense, shields deflect all frontal attacks")
                .addPhase("Frost Empowerment — discards shield, attacks gain AoE frost damage")
                .addPhase("Berserker Rage — doubles attack speed, Quen signs critical to survival")
                .addSpecialProperty("Weakness", "Igni Sign, Elementa Oil")
                .addSpecialProperty("Immunity", "Bleed, Poison")
                .addSpecialProperty("Trait", "Shield blocks all frontal attacks in Phase 1")
                .build();
    }

    public static Enemy createRaidBoss() {
        return new BossEnemyBuilder()
                .withType(BossType.EREDIN)
                .withName("Eredin Bréacc Glas")
                .withLevel(35)
                .withHealth(5500)
                .withDamage(260)
                .withDefence(130)
                .withAgility(95)
                .withFactory(new WildHuntFactory())
                .withLootTable(new WildHuntLootTable())
                .withLoreDescription("King of the Wild Hunt. Commands spectral riders who traverse worlds hunting Elder Blood. Murdered King Auberon to seize the throne of Aen Elle.")
                .addPhase("Spectral Assault — rapid frost sword strikes and portal dashes")
                .addPhase("Rider Summons — calls two Wild Hunt warriors to fight alongside him")
                .addPhase("Veil of Chaos — reality tears open, ice shards rain from portals")
                .addPhase("King's Wrath — fully enraged, all damage doubled, constant frost aura")
                .addSpecialProperty("Weakness", "Aard Sign, Quen, Golden Oriole")
                .addSpecialProperty("Immunity", "Steel weapons, Bleed, Poison, Fire")
                .addSpecialProperty("Trait", "Teleports behind the player when below 50% HP")
                .addSpecialProperty("Loot", "Eredin Trophy, Caretaker's Spade, Naglfar Key")
                .build();
    }

    public static Enemy createDLCBoss() {
        return new BossEnemyBuilder()
                .withType(BossType.DETLAFF)
                .withName("Detlaff the Vampire")
                .withLevel(45)
                .withHealth(7000)
                .withDamage(330)
                .withDefence(150)
                .withAgility(135)
                .withFactory(new VampireFactory())
                .withLootTable(new VampireLootTable())
                .withLoreDescription("A Higher Vampire bound by an old code of honor and loyalty. Manipulated into murdering the knights of Toussaint, Dettlaff unleashes monstrous wrath when betrayed. His power borders on immortal - only another Higher Vampire can truly end him.")
                .addPhase("Stalker of the Night - lightning-fast claws, short-range teleports, bat swarms, punishes greedy melee")
                .addPhase("Crimson Blood Magic - blood spikes erupt from the ground, wide-area slashes, blood pools that force repositioning")
                .addPhase("True Higher Vampire Form - winged transformation, dive-bombs and aerial rushes, relentless multi-hit combos")
                .addPhase("Heart of Darkness - dragged into a living nightmare realm; destroy pulsing hearts while avoiding lethal tendrils")
                .addSpecialProperty("Weakness", "Vampire Oil, Black Blood, Igni Sign, Yrden, Moon Dust")
                .addSpecialProperty("Immunity", "Bleed, Poison, Fear, Charm/Axii control")
                .addSpecialProperty("Trait", "Transforms below ~60% HP; gains flight attacks and aggression ramps up each phase")
                .addSpecialProperty("Loot", "Dettlaff Trophy, Higher Vampire Mutagen, Vampire Fang, Blood-soaked Relic")
                .build();
    }
}
