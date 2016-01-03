package com.boredombabies.charactersheet.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.boredombabies.charactersheet.helper.SpellCastingHelper;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by mark.knutson on 1/2/16.
 */
public class SpellSlot extends RealmObject implements ParentListItem {
    private String level;
    private String slotsTotal;
    private String slotsExpended;
    private RealmList<Spell> spells = new RealmList<>();

    @Ignore
    private List<?> childItemList;
    @Ignore
    private boolean initiallyExpanded = true;

    public SpellSlot() {
        this(null);
    }

    public SpellSlot(Integer level) {
        if (level != null) {
            this.level = Integer.toString(level);
            //SpellCastingHelper.getSpellsPerSlotLevel(level)
            for (int i = 0; i < 2; i++) {
                spells.add(new Spell());
            }
        }
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getSlotsTotal() {
        return slotsTotal;
    }
    public void setSlotsTotal(String slotsTotal) {
        this.slotsTotal = slotsTotal;
    }
    public String getSlotsExpended() {
        return slotsExpended;
    }
    public void setSlotsExpended(String slotsExpended) {
        this.slotsExpended = slotsExpended;
    }
    public RealmList<Spell> getSpells() {
        return spells;
    }
    public void setSpells(RealmList<Spell> spells) {
        this.spells = spells;
    }


    @Override
    public List<?> getChildItemList() {
        return getSpells();
    }
    @Override
    public boolean isInitiallyExpanded() {
        return initiallyExpanded;
    }
}
