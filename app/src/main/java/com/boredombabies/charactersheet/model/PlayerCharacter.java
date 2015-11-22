package com.boredombabies.charactersheet.model;

import com.boredombabies.charactersheet.model.race.CharacterRace;
import com.boredombabies.charactersheet.model.klass.CharacterClass;

import java.util.UUID;

import javax.inject.Inject;

import dagger.Module;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Module
public class PlayerCharacter extends RealmObject {

    private String id;
    private String name;
//    private CharacterRace characterRace;
//    private CharacterClass characterClass;
    private Profile profile;

    public PlayerCharacter() {}

    @Inject
    public PlayerCharacter(
//                         CharacterClass characterClass,
//                         CharacterRace characterRace,
                           Profile profile
                          ) {
//        this.characterClass = characterClass;
//        this.characterRace  = characterRace;
        this.id = UUID.randomUUID().toString();
        this.name = "New Character";
        this.profile = profile;
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
}
