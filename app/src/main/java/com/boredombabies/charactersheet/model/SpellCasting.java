package com.boredombabies.charactersheet.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by mark.knutson on 1/2/16.
 */
public class SpellCasting extends RealmObject {
    private String spellCastingAbility;
    private String spellCastingDC;
    private String spellAttackBonus;
    private RealmList<SpellSlot> spellSlots = new RealmList<>();

    public SpellCasting() {
        for (int i = 0; i < 2; i++) {
            spellSlots.add(new SpellSlot(i));
        }
    }

    public String getSpellCastingAbility() {
        return spellCastingAbility;
    }
    public void setSpellCastingAbility(String spellCastingAbility) {
        this.spellCastingAbility = spellCastingAbility;
    }
    public String getSpellCastingDC() {
        return spellCastingDC;
    }
    public void setSpellCastingDC(String spellCastingDC) {
        this.spellCastingDC = spellCastingDC;
    }
    public String getSpellAttackBonus() {
        return spellAttackBonus;
    }
    public void setSpellAttackBonus(String spellAttackBonus) {
        this.spellAttackBonus = spellAttackBonus;
    }
    public RealmList<SpellSlot> getSpellSlots() {
        return spellSlots;
    }
    public void setSpellSlots(RealmList<SpellSlot> spellSlots) {
        this.spellSlots = spellSlots;
    }
}
