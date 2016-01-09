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
    private SpellCasting spellCasting;
    private Features features;
    private Equipment equipment;
    private Allies allies;

    public PlayerCharacter() {}

    @Inject
    public PlayerCharacter(
//                         CharacterClass characterClass,
//                         CharacterRace characterRace,
                           Profile profile,
                           Attributes attributes,
                           CombatStats combatStats,
                           SpellCasting spellCasting,
                           Features features,
                           Equipment equipment,
                           Allies allies
                          ) {
        this.id = UUID.randomUUID().toString();
        this.profile = profile;
        this.attributes = attributes;
        this.combatStats = combatStats;
        this.spellCasting = spellCasting;
        this.features = features;
        this.equipment = equipment;
        this.allies = allies;
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
    public SpellCasting getSpellCasting() {
        return spellCasting;
    }
    public void setSpellCasting(SpellCasting spellCasting) {
        this.spellCasting = spellCasting;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Allies getAllies() {
        return allies;
    }

    public void setAllies(Allies allies) {
        this.allies = allies;
    }
}
