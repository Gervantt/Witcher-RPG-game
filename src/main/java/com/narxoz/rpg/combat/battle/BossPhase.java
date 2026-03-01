package main.java.com.narxoz.rpg.combat.battle;

import main.java.com.narxoz.rpg.combat.ability.Ability;
import java.util.List;

public class BossPhase {

    private final String phaseName;
    private final double hpThreshold;
    private final List<Ability> phaseAbilities;
    private final double damageMultiplier;
    private final double defenceMultiplier;
    private final String entranceLine;

    public BossPhase(String phaseName, double hpThreshold, List<Ability> phaseAbilities,
                     double damageMultiplier, double defenceMultiplier, String entranceLine) {
        this.phaseName = phaseName;
        this.hpThreshold = hpThreshold;
        this.phaseAbilities = phaseAbilities;
        this.damageMultiplier = damageMultiplier;
        this.defenceMultiplier = defenceMultiplier;
        this.entranceLine = entranceLine;
    }

    public String getPhaseName() { return phaseName; }
    public double getHpThreshold() { return hpThreshold; }
    public List<Ability> getPhaseAbilities() { return phaseAbilities; }
    public double getDamageMultiplier() { return damageMultiplier; }
    public double getDefenceMultiplier() { return defenceMultiplier; }
    public String getEntranceLine() { return entranceLine; }
}