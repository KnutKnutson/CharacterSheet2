package com.boredombabies.charactersheet.module;

import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.model.Profile;
import com.boredombabies.charactersheet.model.klass.CharacterClass;
import com.boredombabies.charactersheet.model.race.CharacterRace;

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
//                new CharacterClass(),
//                new CharacterRace(),
                new Profile()
        );
    }

    @Provides
    CharacterClass provideCharacterClass() {
        return new CharacterClass();
    }

    @Provides
    CharacterRace provideCharacterRace() {
        return new CharacterRace();
    }

    @Provides
    Profile provideProfile() {
        return new Profile();
    }
}
