package com.boredombabies.charactersheet.model;

import com.boredombabies.charactersheet.model.race.CharacterRace;
import com.boredombabies.charactersheet.model.klass.CharacterClass;

import javax.inject.Inject;

import dagger.Module;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Module
public class PlayerCharacter extends RealmObject {

//    private CharacterRace characterRace;
//    private CharacterClass characterClass;
    private Profile profile;

    public PlayerCharacter() {}

    @Inject
    public PlayerCharacter(
//            CharacterClass characterClass,
//            CharacterRace characterRace,
            Profile profile
            ) {
//        this.characterClass = characterClass;
//        this.characterRace  = characterRace;
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
