package com.boredombabies.charactersheet.helper;

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

    static public Integer getSavingThrowBonus(Skill skill) {
        PlayerCharacter playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        Integer modifier = getAbilityScore(
                               skill.getSkillName()
                           ).getAbilityModifier();
        Integer bonus = (skill.isTrained() ? playerCharacter.getAttributes().getProficiencyBonus() : 0);
        return modifier + bonus;
    }

    static public Integer getSkillBonus(Skill skill) {
        PlayerCharacter playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        Integer modifier = getAbilityScore(
                               Constants.BASE_SKILL.valueOf(
                                   skill.getSkillName()
                               ).skillModifier()
                           ).getAbilityModifier();
        Integer bonus = (skill.isTrained() ? playerCharacter.getAttributes().getProficiencyBonus() : 0);
        return modifier + bonus;
    }

    static public int getClassImage(String className) {
        switch (className.toLowerCase()) {
            case "barbarian":
                return R.drawable.barbarian_small;
            case "bard":
                return R.drawable.bard_small;
            case "cleric":
                return R.drawable.cleric_small;
            case "druid":
                return R.drawable.druid_small;
            case "fighter":
                return R.drawable.fighter_small;
            case "monk":
                return R.drawable.monk_small;
            case "paladin":
                return R.drawable.paladin_small;
            case "ranger":
                return R.drawable.ranger_small;
            case "rogue":
                return R.drawable.rogue_small;
            case "sorcerer":
                return R.drawable.sorcerer_small;
            case "warlock":
                return R.drawable.warlock_small;
            case "wizard":
                return R.drawable.wizard_small;
            default:
                return 0;
        }
    }

    public static Skill getSkill(String skill) {
        for (Skill s : PlayerCharacterHelper.getActiveCharacter().getAttributes().getSkills()) {
            if (s.getSkillName() == skill) {
                return s;
            }
        }
        return null;
    }

    public static Skill getSavingThrow(String abilityScore) {
        for (Skill s : PlayerCharacterHelper.getActiveCharacter().getAttributes().getSavingThrows()) {
            if (s.getSkillName() == abilityScore) {
                return s;
            }
        }
        return null;
    }

    public static AbilityScore getAbilityScore(String abilityScore) {
        for (AbilityScore as : PlayerCharacterHelper.getActiveCharacter().getAttributes().getAbilityScores()) {
            if (as.getName() == abilityScore) {
                return as;
            }
        }
        return null;
    }
}
