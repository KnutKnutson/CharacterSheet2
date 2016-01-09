package com.boredombabies.charactersheet.module;

import com.boredombabies.charactersheet.model.Allies;
import com.boredombabies.charactersheet.model.Attributes;
import com.boredombabies.charactersheet.model.CombatStats;
import com.boredombabies.charactersheet.model.Equipment;
import com.boredombabies.charactersheet.model.Features;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.model.Profile;
import com.boredombabies.charactersheet.model.SpellCasting;

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
            new Profile(),
            new Attributes(),
            new CombatStats(),
            new SpellCasting(),
            new Features(),
            new Equipment(),
            new Allies()
        );
    }

    @Provides
    Profile provideProfile() {
        return new Profile();
    }

    @Provides
    Attributes provideAttributes() {
        return new Attributes();
    }

    @Provides
    CombatStats provideCombatStats() { return new CombatStats(); }

    @Provides
    SpellCasting provideSpellCasting() { return new SpellCasting(); }

    @Provides
    Features provideFeatures() { return new Features(); }

    @Provides
    Equipment provideEquipment() { return new Equipment(); }

    @Provides
    Allies provideAllies() { return new Allies(); }
}
