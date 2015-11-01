package com.boredombabies.charactersheet.model;


/**
 * Created by mark.knutson on 5/5/15.
 */
public class AbilityScore {

    private String name;
    private String abilityScore;
    private String abilityModifier;
    private PlayerCharacter playerCharacter;

    public AbilityScore() {
        super();
    }

    public AbilityScore(PlayerCharacter playerCharacter) {
        super();
        this.playerCharacter = playerCharacter;
    }

    public AbilityScore(PlayerCharacter playerCharacter, String name) {
        super();
        this.playerCharacter = playerCharacter;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbilityScore() {
        return abilityScore;
    }

    public void setAbilityScore(String abilityScore) {
        this.abilityScore = abilityScore;
    }

    public String getAbilityModifier() {
        return abilityModifier;
    }

    public void setAbilityModifier(String abilityModifier) {
        this.abilityModifier = abilityModifier;
    }

    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
    }
}
