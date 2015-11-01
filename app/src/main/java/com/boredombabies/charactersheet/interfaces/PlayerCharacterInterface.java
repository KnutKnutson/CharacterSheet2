package com.boredombabies.charactersheet.interfaces;

import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.module.PlayerCharacterModule;

import dagger.Component;

/**
 * Created by mark.knutson on 10/31/15.
 */

@Component(modules = PlayerCharacterModule.class)
public interface PlayerCharacterInterface {
    PlayerCharacter providePlayerCharacter();
}
