package com.boredombabies.charactersheet.model;

import java.util.UUID;

import javax.inject.Inject;

import dagger.Module;
import io.realm.RealmObject;

@Module
public class PlayerCharacter extends RealmObject {

    private String id;
    private String name = "New Character";
    private String characterRace = "";
    private String characterClass = "";
    private Profile profile;
    private Attributes attributes;
    private CombatStats combatStats;

    public PlayerCharacter() {}

    @Inject
    public PlayerCharacter(
//                         CharacterClass characterClass,
//                         CharacterRace characterRace,
                           Profile profile,
                           Attributes attributes,
                           CombatStats combatStats
                          ) {
        this.id = UUID.randomUUID().toString();
        this.profile = profile;
        this.attributes = attributes;
        this.combatStats = combatStats;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public String getCharacterRace() {
        return characterRace;
    }
    public void setCharacterRace(String characterRace) {
        this.characterRace = characterRace;
    }
    public String getCharacterClass() {
        return characterClass;
    }
    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }
    public Attributes getAttributes() {
        return attributes;
    }
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
    public CombatStats getCombatStats() {
        return combatStats;
    }
    public void setCombatStats(CombatStats combatStats) {
        this.combatStats = combatStats;
    }
}
