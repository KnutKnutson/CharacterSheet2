package com.boredombabies.charactersheet.helper;

import android.content.Context;

import com.boredombabies.charactersheet.interfaces.DaggerPlayerCharacterInterface;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.module.PlayerCharacterModule;

import dagger.Module;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mark.knutson on 10/31/15.
 */

public class PlayerCharacterHelper {
    private static RealmResults<PlayerCharacter> characters;
    private static PlayerCharacter activeCharacter;

    public static PlayerCharacter newPlayerCharacter(Context context) {
        PlayerCharacter newCharacter =
                DaggerPlayerCharacterInterface.builder()
                .playerCharacterModule(new PlayerCharacterModule())
                .build()
                .providePlayerCharacter();

        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        PlayerCharacter realmCharacter = realm.copyToRealm(newCharacter);
        realm.commitTransaction();
        return realmCharacter;
    }

    public static RealmResults<PlayerCharacter> getCharacters(Context context) {
        if (characters == null) {
            Realm realm = Realm.getInstance(context);
            realm.allObjects( PlayerCharacter.class );
        }
        return characters;
    }
}
