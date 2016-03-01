package com.boredombabies.charactersheet.Activity;

import com.boredombabies.charactersheet.activity.CharacterSheetListActivity;
import com.boredombabies.charactersheet.interfaces.DaggerPlayerCharacterInterface;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.module.PlayerCharacterModule;

import org.junit.Test;

import io.realm.RealmList;

import static org.junit.Assert.*;

/**
 * Created on 2/29/16.
 */
public class CharacterSheetListActivityTest {

    /** Make sure allies are not sent as it may 'update' a character to a previous state */
    @Test
    public void testSerializeCharacter() throws Exception {
        String characterName = "Davey Jones";
        String allyName = "testAlly";

        CharacterSheetListActivity csla = new CharacterSheetListActivity();
        PlayerCharacter ally = DaggerPlayerCharacterInterface.builder()
                .playerCharacterModule(new PlayerCharacterModule())
                .build()
                .providePlayerCharacter();

        ally.setName(allyName);
        RealmList<PlayerCharacter> allies = new RealmList<>();
        allies.add(ally);

        PlayerCharacter characterToSend = DaggerPlayerCharacterInterface.builder()
                .playerCharacterModule(new PlayerCharacterModule())
                .build()
                .providePlayerCharacter();
        characterToSend.setName(characterName);
        characterToSend.getAllies().setPlayerCharacterAllies(allies);

        assertFalse(csla.serializeCharacter(characterToSend).contains(allyName));
        assertTrue(csla.serializeCharacter(characterToSend).contains(characterName));
    }
}
