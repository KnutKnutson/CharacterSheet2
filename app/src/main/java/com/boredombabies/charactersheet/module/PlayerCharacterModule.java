package com.boredombabies.charactersheet.module;

import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.model.Profile;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mark.knutson on 10/31/15.
 */

@Module
public class PlayerCharacterModule {

    @Provides
    PlayerCharacter providePlayerCharacter() {
        return new PlayerCharacter(
            new Profile()
        );
    }

    @Provides
    Profile provideProfile() {
        return new Profile();
    }
}
