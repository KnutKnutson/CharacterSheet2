package com.boredombabies.charactersheet.helper;

import android.util.Log;

import com.boredombabies.charactersheet.interfaces.DaggerPlayerCharacterInterface;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.module.PlayerCharacterModule;

import io.realm.Realm;
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

    public static RealmResults<PlayerCharacter> assembleParty(Realm realm) {
        if (characters == null) {
            characters = realm.where( PlayerCharacter.class )
                              .equalTo("deleted", false)
                              .findAll();
            // Ideally this would sort by last updated, but that's not currently tracked.
            characters.sort("name");
        }
        return characters;
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
}
