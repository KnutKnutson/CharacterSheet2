package com.boredombabies.charactersheet.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created on 1/9/16.
 */
public class Allies extends RealmObject {
    private String allies;
    private transient RealmList<PlayerCharacter> playerCharacterAllies;
    private String organizationName;

    public Allies() {}

    public String getAllies() {
        return allies;
    }

    public void setAllies(String allies) {
        this.allies = allies;
    }

    public RealmList<PlayerCharacter> getPlayerCharacterAllies() {
        return playerCharacterAllies;
    }

    public void setPlayerCharacterAllies(RealmList<PlayerCharacter> playerCharacterAllies) {
        this.playerCharacterAllies = playerCharacterAllies;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
