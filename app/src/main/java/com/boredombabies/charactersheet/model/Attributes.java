package com.boredombabies.charactersheet.model;

import com.boredombabies.charactersheet.helper.Constants;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by mark.knutson on 11/29/15.
 */
public class Attributes extends RealmObject {
    private int inspiration = 0;
    private int proficiencyBonus = 0;
    private int passiveWisdom = 0;
    private RealmList<AbilityScore> abilityScores = new RealmList<>();
    private RealmList<Skill> savingThrows = new RealmList<>();
    private RealmList<Skill> skills = new RealmList<>();

    @Ignore
    private AbilityScore strAbilityScore;
    @Ignore
    private AbilityScore dexAbilityScore;
    @Ignore
    private AbilityScore conAbilityScore;
    @Ignore
    private AbilityScore intAbilityScore;
    @Ignore
    private AbilityScore wisAbilityScore;
    @Ignore
    private AbilityScore chaAbilityScore;

    public Attributes() {
        for (Constants.ABILITY_SCORE abilityScore : Constants.ABILITY_SCORE.values()) {
            abilityScores.add(new AbilityScore(abilityScore.modifier()));
        }
        for (Constants.ABILITY_SCORE abilityScore : Constants.ABILITY_SCORE.values()) {
            savingThrows.add(new Skill(abilityScore.modifier()));
        }
        for (Constants.BASE_SKILL baseSkill : Constants.BASE_SKILL.values()) {
            skills.add(new Skill(baseSkill.skillName(), baseSkill.skillModifierShort()));
        }
    }

    public AbilityScore getStrAbilityScore() {
        for (AbilityScore as : abilityScores) {
            if (as.getName().equals(Constants.ABILITY_SCORE.ABILITY_SCORE_STRENGTH)) {
                return as;
            }
        }
        return null;
    }
    public AbilityScore getDexAbilityScore() {
        for (AbilityScore as : abilityScores) {
            if (as.getName().equals(Constants.ABILITY_SCORE.ABILITY_SCORE_DEXTERITY)) {
                return as;
            }
        }
        return null;
    }
    public AbilityScore getConAbilityScore() {
        for (AbilityScore as : abilityScores) {
            if (as.getName().equals(Constants.ABILITY_SCORE.ABILITY_SCORE_CONSTITUTION)) {
                return as;
            }
        }
        return null;
    }
    public AbilityScore getIntAbilityScore() {
        for (AbilityScore as : abilityScores) {
            if (as.getName().equals(Constants.ABILITY_SCORE.ABILITY_SCORE_INTELLIGENCE)) {
                return as;
            }
        }
        return null;
    }
    public AbilityScore getWisAbilityScore() {
        for (AbilityScore as : abilityScores) {
            if (as.getName().equals(Constants.ABILITY_SCORE.ABILITY_SCORE_WISDOM)) {
                return as;
            }
        }
        return null;
    }
    public AbilityScore getChaAbilityScore() {
        for (AbilityScore as : abilityScores) {
            if (as.getName().equals(Constants.ABILITY_SCORE.ABILITY_SCORE_CHARISMA)) {
                return as;
            }
        }
        return null;
    }

    public RealmList<AbilityScore> getAbilityScores() {
        return abilityScores;
    }
    public void setAbilityScores(RealmList<AbilityScore> abilityScores) {
        this.abilityScores = abilityScores;
    }
    public RealmList<Skill> getSavingThrows() {
        return savingThrows;
    }
    public void setSavingThrows(RealmList<Skill> savingThrows) {
        this.savingThrows = savingThrows;
    }
    public RealmList<Skill> getSkills() {
        return skills;
    }
    public void setSkills(RealmList<Skill> skills) {
        this.skills = skills;
    }
    public int getInspiration() {
        return inspiration;
    }
    public void setInspiration(int inspiration) {
        this.inspiration = inspiration;
    }
    public int getProficiencyBonus() {
        return proficiencyBonus;
    }
    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }
    public int getPassiveWisdom() {
        return passiveWisdom;
    }
    public void setPassiveWisdom(int passiveWisdom) {
        this.passiveWisdom = passiveWisdom;
    }
}
