package com.boredombabies.charactersheet.model;


import io.realm.RealmObject;

/**
 * Created by mark.knutson on 4/7/15.
 */
public class Skill extends RealmObject {
    private boolean trained = false;
    private String skillBonus = "0";
    private String skillName;
    private String skillAbilityModifier;
    private String skillAbilityModLong;

    public Skill() {
    }
    public Skill(String name) {
        this.skillName = name;
    }
    public Skill(String name, String modifier, String modifierLong) {
        this.skillName = name;
        this.skillAbilityModifier = modifier;
        this.skillAbilityModLong = modifierLong;
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

    public String getSkillAbilityModLong() {
        return skillAbilityModLong;
    }

    public void setSkillAbilityModLong(String skillAbilityModLong) {
        this.skillAbilityModLong = skillAbilityModLong;
    }
}
