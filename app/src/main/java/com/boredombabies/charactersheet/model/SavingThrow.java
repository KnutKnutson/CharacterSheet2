package com.boredombabies.charactersheet.model;


/**
 * Created by mark.knutson on 5/5/15.
 */
public class SavingThrow {
    private boolean trained;
    private String skillBonus;
    private String skillName;
    private String skillAbilityModifier;

    private PlayerCharacter playerCharacter;

    public SavingThrow() {
        super();
    }

    public SavingThrow(PlayerCharacter playerCharacter) {
        super();
        this.playerCharacter = playerCharacter;
    }

    public SavingThrow(PlayerCharacter playerCharacter, String name) {
        super();
        this.playerCharacter = playerCharacter;
        this.skillName = name;
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
