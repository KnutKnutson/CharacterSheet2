package com.boredombabies.charactersheet.db;

import android.content.Context;

import com.boredombabies.charactersheet.db.migration.CharacterSheetMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created on 3/7/16.
 */
public class RealmHelper {

    public static Realm getRealm(Context context) {
        return Realm.getInstance(getConfig(context));
    }

    public static RealmConfiguration getConfig(Context context) {
        return new RealmConfiguration.Builder(context)
                .name("myrealm.realm")
                        //.encryptionKey(getKey())
                .schemaVersion(1)
                        //.setModules(new MySchemaModule())
                .migration(new CharacterSheetMigration())
                .build();
    }

    public static RealmConfiguration getDebugConfig(Context context) {
        return new RealmConfiguration.Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();
    }
}

