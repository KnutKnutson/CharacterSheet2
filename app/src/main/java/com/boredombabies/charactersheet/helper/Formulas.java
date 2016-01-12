package com.boredombabies.charactersheet.helper;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.model.Skill;

/**
 * Created by mark.knutson on 12/7/15.
 */
public class Formulas {
    
    static public String getInitiative(PlayerCharacter playerCharacter) {
        return playerCharacter.getCombatStats().getInitiative();
    }

    static public Integer getSkillBonus(Skill skill, PlayerCharacter playerCharacter) {
        Integer modifier = getAbilityModifier(skill.getSkillAbilityModifier(), playerCharacter);
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

    public static Integer getAbilityModifier(String ability, PlayerCharacter playerCharacter) {
        switch (ability) {
            case Constants.STR:
                return playerCharacter.getAttributes().getStrAbilityScore().getAbilityModifier();
            case Constants.DEX:
                return playerCharacter.getAttributes().getDexAbilityScore().getAbilityModifier();
            case Constants.CON:
                return playerCharacter.getAttributes().getConAbilityScore().getAbilityModifier();
            case Constants.INT:
                return playerCharacter.getAttributes().getIntAbilityScore().getAbilityModifier();
            case Constants.WIS:
                return playerCharacter.getAttributes().getWisAbilityScore().getAbilityModifier();
            case Constants.CHA:
                return playerCharacter.getAttributes().getChaAbilityScore().getAbilityModifier();
            default:
                return 0;
        }
    }
}
