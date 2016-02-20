package com.boredombabies.charactersheet.helper;

import android.util.Log;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.model.AbilityScore;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.model.Skill;

/**
 * Created by mark.knutson on 12/7/15.
 */
public class Formulas {

    static public String getInitiative() {
        return PlayerCharacterHelper.getActiveCharacter().getCombatStats().getInitiative();
    }

    static public String getPassiveWisdom() {
        return Integer.toString(getSkillBonus(getSkill(Constants.PERCEPTION)));
    }

    static public Integer getSkillBonus(Skill skill) {
        PlayerCharacter playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        Integer modifier = getAbilityScore(
                               skill.getSkillAbilityModLong()
                           ).getAbilityModifier();
        Integer bonus = (skill.isTrained() ? playerCharacter.getAttributes().getProficiencyBonus() : 0);
        return modifier + bonus;
    }

    static public int getHeaderImage(String className) {
        switch (className.toLowerCase().trim()) {
            case "barbarian":
                return R.drawable.header_barbarian;
            case "bard":
                return R.drawable.header_bard;
            case "cleric":
                return R.drawable.header_cleric;
            case "druid":
                return R.drawable.header_druid;
            case "fighter":
                return R.drawable.header_fighter;
            case "monk":
                return R.drawable.header_monk;
            case "paladin":
                return R.drawable.header_paladin;
            case "ranger":
                return R.drawable.header_ranger;
            case "rogue":
                return R.drawable.header_rogue;
            case "sorcerer":
                return R.drawable.header_sorcerer;
            case "warlock":
                return R.drawable.header_warlock;
            case "wizard":
                return R.drawable.header_wizard;
            default:
                return 0;
        }
    }

    static public int getHeadShotImage(String className) {
        switch (className.toLowerCase().trim()) {
            case "barbarian":
                return R.drawable.headshot_barbarian;
            case "bard":
                return R.drawable.headshot_bard;
            case "cleric":
                return R.drawable.headshot_cleric;
            case "druid":
                return R.drawable.headshot_druid;
            case "fighter":
                return R.drawable.headshot_fighter;
            case "monk":
                return R.drawable.headshot_monk;
            case "paladin":
                return R.drawable.headshot_paladin;
            case "ranger":
                return R.drawable.headshot_ranger;
            case "rogue":
                return R.drawable.headshot_rogue;
            case "sorcerer":
                return R.drawable.headshot_sorcerer;
            case "warlock":
                return R.drawable.headshot_warlock;
            case "wizard":
                return R.drawable.headshot_wizard;
            default:
                return 0;
        }
    }

    public static Skill getSkill(String skill) {
        for (Skill s : PlayerCharacterHelper.getActiveCharacter().getAttributes().getSkills()) {
            if (s.getSkillName().equals(skill)) {
                return s;
            }
        }
        return null;
    }

    public static Skill getSavingThrow(String abilityScore) {
        for (Skill s : PlayerCharacterHelper.getActiveCharacter().getAttributes().getSavingThrows()) {
            if (s.getSkillName().equals(abilityScore)) {
                return s;
            }
        }
        return null;
    }

    public static AbilityScore getAbilityScore(String abilityScore) {
        for (AbilityScore as : PlayerCharacterHelper.getActiveCharacter().getAttributes().getAbilityScores()) {
            if (as.getName().equals(abilityScore)) {
                return as;
            }
        }
        return null;
    }
}
