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
    private String otherProficienciesAndLanguages;

    public Attributes() {
        for (Constants.ABILITY_SCORE abilityScore : Constants.ABILITY_SCORE.values()) {
            abilityScores.add(new AbilityScore(abilityScore.modifier()));
        }
        for (Constants.ABILITY_SCORE abilityScore : Constants.ABILITY_SCORE.values()) {
            savingThrows.add(new Skill(abilityScore.modifier(), abilityScore.modifierShort(), abilityScore.modifier()));
        }
        for (Constants.BASE_SKILL baseSkill : Constants.BASE_SKILL.values()) {
            skills.add(new Skill(baseSkill.skillName(), baseSkill.skillModifierShort(), baseSkill.skillModifier()));
        }
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

    public String getOtherProficienciesAndLanguages() {
        return otherProficienciesAndLanguages;
    }

    public void setOtherProficienciesAndLanguages(String otherProficienciesAndLanguages) {
        this.otherProficienciesAndLanguages = otherProficienciesAndLanguages;
    }
}
