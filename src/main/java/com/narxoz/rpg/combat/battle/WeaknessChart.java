package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.combat.ability.Ability;

import java.util.HashMap;
import java.util.Map;

public class WeaknessChart {
    public static final double WEAKNESS_MULTIPLIER = 1.5;

    private static final Map<String, String> WEAKNESS_MAP = new HashMap<>();

    static {
        // wraiths
        WEAKNESS_MAP.put("Wraith", "yrden");
        WEAKNESS_MAP.put("Noonwraith", "yrden");
        WEAKNESS_MAP.put("Nightwraith", "yrden");

        // vampires
        WEAKNESS_MAP.put("Katakan", "igni");
        WEAKNESS_MAP.put("Ekimmara", "igni");

        // undead enemies
        WEAKNESS_MAP.put("Ghoul", "igni");
        WEAKNESS_MAP.put("Nekker", "igni");

        // drowner
        WEAKNESS_MAP.put("Drowner", "igni");

        // Wildhunt
        WEAKNESS_MAP.put("Eredin", "igni");
        WEAKNESS_MAP.put("Imlerith", "igni");

        // UpperVampire
        WEAKNESS_MAP.put("Detlaff", "yrden");
    }

    public static boolean isWeak(String enemyName, Ability ability) {
        if (enemyName == null || ability == null) return false;

        String weaknessSchool = null;
        for (Map.Entry<String, String> entry : WEAKNESS_MAP.entrySet()) {
            if (enemyName.toLowerCase().contains(entry.getKey().toLowerCase())) {
                weaknessSchool = entry.getValue();
                break;
            }
        }

        if (weaknessSchool == null) return false;

        String abilityClass = ability.getClass().getName().toLowerCase();
        return abilityClass.contains("." + weaknessSchool + ".");
    }

    public static String getWeaknessSchool(String enemyName) {
        for (Map.Entry<String, String> entry : WEAKNESS_MAP.entrySet()) {
            if (enemyName.toLowerCase().contains(entry.getKey().toLowerCase())) {
                return entry.getValue().substring(0, 1).toUpperCase() + entry.getValue().substring(1);
            }
        }
        return "None";
    }
}