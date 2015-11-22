package com.boredombabies.charactersheet.helper;

import android.content.Context;

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

    public static PlayerCharacter newPlayerCharacter(Realm realm) {
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

    public static void deletePlayerCharacter(Realm realm, PlayerCharacter honoredDead) {
        realm.beginTransaction();
        honoredDead.removeFromRealm();
        realm.commitTransaction();
    }

    public static RealmResults<PlayerCharacter> getCharacters(Realm realm) {
        if (characters == null) {
            characters = realm.allObjects( PlayerCharacter.class );
            characters.sort("name");
        }
        return characters;
    }

    public static PlayerCharacter getActiveCharacter() {
        return activeCharacter;
    }

    public static void setActiveCharacter(PlayerCharacter playerCharacter) {
        activeCharacter = playerCharacter;
    }
}
