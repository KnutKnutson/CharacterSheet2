package com.boredombabies.charactersheet.helper;

import android.util.Log;

import com.boredombabies.charactersheet.interfaces.DaggerPlayerCharacterInterface;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.module.PlayerCharacterModule;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by mark.knutson on 10/31/15.
 */

public class PlayerCharacterHelper {
    private static RealmResults<PlayerCharacter> characters;
    private static PlayerCharacter activeCharacter;

    public static PlayerCharacter newCharacter(Realm realm) {
        PlayerCharacter newCharacter =
                DaggerPlayerCharacterInterface.builder()
                .playerCharacterModule(new PlayerCharacterModule())
                .build()
                .providePlayerCharacter();

        realm.beginTransaction();
        PlayerCharacter realmCharacter = realm.copyToRealm(newCharacter);
        realm.commitTransaction();

        setActiveCharacter(realmCharacter);
        return getActiveCharacter();
    }

    public static void killCharacter(Realm realm, PlayerCharacter honoredDead) {
        realm.beginTransaction();
        honoredDead.setDeleted(true);
        realm.commitTransaction();
    }

    public static void resurrectCharacter(Realm realm, PlayerCharacter fortunateSoul) {
        realm.beginTransaction();
        fortunateSoul.setDeleted(false);
        realm.commitTransaction();
    }

    public static RealmResults<PlayerCharacter> getPotentialAllies(Realm realm) {
        RealmQuery<PlayerCharacter> allyQuery = realm.where(PlayerCharacter.class);
        allyQuery.equalTo("deleted", false);
        allyQuery.notEqualTo("id", getActiveCharacter().getId());
        for (String uuid : getAllyUUIDS()) {
            allyQuery.notEqualTo("id", uuid);
        }
        RealmResults<PlayerCharacter> potentialAllies = allyQuery.findAll();
        potentialAllies.sort("name");
        return potentialAllies;
    }

    public static RealmResults<PlayerCharacter> assembleParty(Realm realm) {
        if (characters == null) {
            // TODO: clean up deleted characters
            characters = realm.where( PlayerCharacter.class )
                              .equalTo("deleted", false)
                              .findAll();
            // Ideally this would sort by last updated, but that's not currently tracked.
            characters.sort("name");
        }
        return characters;
    }

    public static RealmResults<PlayerCharacter> findAllies() {
        Realm realm = Realm.getDefaultInstance();
        return null;
    }

    public static PlayerCharacter getCharacter(Realm realm, int partyMember) {
        return assembleParty(realm).get(partyMember);
    }

    public static PlayerCharacter getActiveCharacter() {
        return activeCharacter;
    }

    public static void setActiveCharacter(PlayerCharacter playerCharacter) {
        activeCharacter = playerCharacter;
    }

    public static List<String> getAllyUUIDS() {
        List<String> uuids = new ArrayList<>();
        for (PlayerCharacter ally : getActiveCharacter().getAllies().getPlayerCharacterAllies()) {
            uuids.add(ally.getId());
        }
        return uuids;
    }
}
