package com.boredombabies.charactersheet.model;


/**
 * Created by mark.knutson on 4/7/15.
 */
public class Skill {
    private boolean trained;
    private String skillBonus;
    private String skillName;
    private String skillAbilityModifier;

    private PlayerCharacter playerCharacter;

    public Skill() {
        super();
    }

    public Skill(PlayerCharacter pc) {
        super();
        this.playerCharacter = pc;
    }

    public Skill(PlayerCharacter pc, boolean trained, String skillBonus, String skillName, String skillAbilityModifier) {
        super();
        this.playerCharacter = pc;
        this.trained = trained;
        this.skillBonus = skillBonus;
        this.skillName = skillName;
        this.skillAbilityModifier = skillAbilityModifier;
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

    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
    }
}
