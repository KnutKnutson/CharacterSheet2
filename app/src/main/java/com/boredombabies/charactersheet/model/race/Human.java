package com.boredombabies.charactersheet.model.race;

import com.boredombabies.charactersheet.interfaces.CharacterRace;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by mark.knutson on 3/22/15.
 */
public class Human implements CharacterRace {
    private String raceName = "Human";

    @Override
    public String getCharacterRace() {
        return raceName;
    }
}
