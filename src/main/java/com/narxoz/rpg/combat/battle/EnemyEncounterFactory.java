package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.enemy.builder.EnemyType;
import main.java.com.narxoz.rpg.enemy.Enemy;
import main.java.com.narxoz.rpg.enemy.builder.BasicEnemyBuilder;
import main.java.com.narxoz.rpg.factory.enemiesfactory.UndeadFactory;
import main.java.com.narxoz.rpg.factory.enemiesfactory.VampireFactory;

import java.util.Arrays;
import java.util.List;

public class EnemyEncounterFactory {

    private static final UndeadFactory undeadFactory = new UndeadFactory();
    private static final VampireFactory vampireFactory = new VampireFactory();

    //  WRAITH ENCOUNTERS
    public static EnemyCombatantAdapter createNoonwraith() {
        Enemy noonWraith = new BasicEnemyBuilder()
                .withType(EnemyType.WRAITH)
                .withName("Noonwraith")
                .withLevel(12)
                .withHealth(280)
                .withDamage(40)
                .withDefence(10)
                .withAgility(45)
                .withAbilities(undeadFactory.createAbilities())
                .withLootTable(undeadFactory.createLootTable())
                .withAIBehavior(undeadFactory.createAIBehavior())
                .build();

        return new EnemyCombatantAdapter(noonWraith);
    }

    public static EnemyCombatantAdapter createNightwraith() {
        Enemy nightWraith = new BasicEnemyBuilder()
                .withType(EnemyType.WRAITH)
                .withName("Nightwraith")
                .withLevel(18)
                .withHealth(380)
                .withDamage(52)
                .withDefence(14)
                .withAgility(55)
                .withAbilities(undeadFactory.createAbilities())
                .withLootTable(undeadFactory.createLootTable())
                .withAIBehavior(undeadFactory.createAIBehavior())
                .build();

        return new EnemyCombatantAdapter(nightWraith);
    }

    public static EnemyCombatantAdapter createKatakan() {
        Enemy katakan = new BasicEnemyBuilder()
                .withType(EnemyType.KATAKAN)
                .withName("Katakan")
                .withLevel(16)
                .withHealth(350)
                .withDamage(48)
                .withDefence(15)
                .withAgility(50)
                .withAbilities(vampireFactory.createAbilities())
                .withLootTable(vampireFactory.createLootTable())
                .withAIBehavior(vampireFactory.createAIBehavior())
                .build();

        return new EnemyCombatantAdapter(katakan);
    }

    public static EnemyCombatantAdapter createEkimmara() {
        Enemy ekimmara = new BasicEnemyBuilder()
                .withType(EnemyType.KATAKAN)
                .withName("Ekimmara")
                .withLevel(13)
                .withHealth(300)
                .withDamage(42)
                .withDefence(12)
                .withAgility(40)
                .withAbilities(vampireFactory.createAbilities())
                .withLootTable(vampireFactory.createLootTable())
                .withAIBehavior(vampireFactory.createAIBehavior())
                .build();

        return new EnemyCombatantAdapter(ekimmara);
    }

    public static EnemyCombatantAdapter createGhoul() {
        Enemy ghoul = new BasicEnemyBuilder()
                .withType(EnemyType.GHOUL)
                .withName("Ghoul")
                .withLevel(8)
                .withHealth(180)
                .withDamage(30)
                .withDefence(8)
                .withAgility(25)
                .withAbilities(undeadFactory.createAbilities())
                .withLootTable(undeadFactory.createLootTable())
                .withAIBehavior(undeadFactory.createAIBehavior())
                .build();

        return new EnemyCombatantAdapter(ghoul);
    }

    public static EnemyCombatantAdapter createAlghoul() {
        Enemy alghoul = new BasicEnemyBuilder()
                .withType(EnemyType.GHOUL)
                .withName("Alghoul")
                .withLevel(15)
                .withHealth(320)
                .withDamage(45)
                .withDefence(18)
                .withAgility(30)
                .withAbilities(undeadFactory.createAbilities())
                .withLootTable(undeadFactory.createLootTable())
                .withAIBehavior(undeadFactory.createAIBehavior())
                .build();

        return new EnemyCombatantAdapter(alghoul);
    }

    public static EnemyCombatantAdapter createNekkerWarrior() {
        Enemy nekker = new BasicEnemyBuilder()
                .withType(EnemyType.NEKKER)
                .withName("Nekker Warrior")
                .withLevel(5)
                .withHealth(100)
                .withDamage(22)
                .withDefence(6)
                .withAgility(40)
                .withAbilities(undeadFactory.createAbilities())
                .withLootTable(undeadFactory.createLootTable())
                .withAIBehavior(undeadFactory.createAIBehavior())
                .build();

        return new EnemyCombatantAdapter(nekker);
    }

    public static EnemyCombatantAdapter createDrowner() {
        Enemy drowner = new BasicEnemyBuilder()
                .withType(EnemyType.DROWNER)
                .withName("Drowner")
                .withLevel(5)
                .withHealth(150)
                .withDamage(30)
                .withDefence(8)
                .withAgility(25)
                .withAbilities(undeadFactory.createAbilities())
                .withLootTable(undeadFactory.createLootTable())
                .withAIBehavior(undeadFactory.createAIBehavior())
                .build();

        return new EnemyCombatantAdapter(drowner);
    }


    //  MIXED ENCOUNTER PACKS

    public static List<Combatant> createGraveyardHaunt() {
        return Arrays.asList(createNightwraith(), createNoonwraith(), createNoonwraith());
    }

    public static List<Combatant> createVampireNest() {
        return Arrays.asList(createKatakan(), createEkimmara());
    }

    public static List<Combatant> createNekkerPack() {
        EnemyCombatantAdapter nekker = createNekkerWarrior();
        return Arrays.asList(nekker, nekker, nekker);
    }

    public static List<Combatant> createDrownerPack() {
        Enemy alpha = new BasicEnemyBuilder()
                .withType(EnemyType.DROWNER)
                .withName("Drowner Alpha")
                .withLevel(6)
                .withHealth(120)
                .withDamage(22)
                .withDefence(5)
                .withAgility(30)
                .withAbilities(undeadFactory.createAbilities())
                .withLootTable(undeadFactory.createLootTable())
                .withAIBehavior(undeadFactory.createAIBehavior())
                .build();

        Enemy basic = new BasicEnemyBuilder()
                .withType(EnemyType.DROWNER)
                .withName("Drowner")
                .withLevel(4)
                .withHealth(80)
                .withDamage(18)
                .withDefence(3)
                .withAgility(35)
                .withAbilities(undeadFactory.createAbilities())
                .withLootTable(undeadFactory.createLootTable())
                .withAIBehavior(undeadFactory.createAIBehavior())
                .build();

        Enemy basic2 = basic.clone();
        Enemy basic3 = basic.clone();

        return Arrays.asList(
                new EnemyCombatantAdapter(alpha),
                new EnemyCombatantAdapter(basic),
                new EnemyCombatantAdapter(basic2),
                new EnemyCombatantAdapter(basic3)
        );
    }
}