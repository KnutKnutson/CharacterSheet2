package com.boredombabies.charactersheet.model;

import com.boredombabies.charactersheet.model.klass.CharacterClass;
import com.boredombabies.charactersheet.model.race.CharacterRace;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.RealmObject;

/**
 * Created by mark.knutson on 3/22/15.
 */
public class Profile extends RealmObject {
    private String name;
    private int level;

    public Profile() {
        this.name = "New Character";
        this.level = 1;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
//    private String name;
//    private String playerName;
//    private String level;
//    private String background;
//    private String alignment;
//    private String experiencePoints;
//    private String age;
//    private String height;
//    private String weight;
//    private String eyes;
//    private String skin;
//    private String hair;
}
