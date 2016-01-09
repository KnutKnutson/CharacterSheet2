package com.boredombabies.charactersheet.model;


import io.realm.RealmObject;

/**
 * Created by mark.knutson on 4/7/15.
 */
public class Skill extends RealmObject {
    private boolean trained = false;
    // TODO: autocalculate skillbonus
    private String skillBonus = "0";
    private String skillName;
    private String skillAbilityModifier;

    public Skill() {
    }
    public Skill(String name) {
        this.skillName = name;
    }
    public Skill(String name, String modifier) {
        this.skillName = name;
        this.skillAbilityModifier = modifier;
    }

    public boolean isTrained() {
        return trained;
    }
    public void setTrained(boolean trained) {
        this.trained = trained;
    }
    public String getSkillBonus() {
        return skillBonus;
    }
    public void setSkillBonus(String skillBonus) {
        this.skillBonus = skillBonus;
    }
    public String getSkillName() {
        return skillName;
    }
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    public String getSkillAbilityModifier() {
        return skillAbilityModifier;
    }
    public void setSkillAbilityModifier(String skillAbilityModifier) {
        this.skillAbilityModifier = skillAbilityModifier;
    }
}
