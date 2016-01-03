package com.boredombabies.charactersheet.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.model.Spell;
import com.boredombabies.charactersheet.model.SpellSlot;

import java.util.List;

/**
 * Created by mark.knutson on 1/2/16.
 */
public class SpellSlotExpandableListAdapter extends
        ExpandableRecyclerAdapter<SpellSlotViewHolder, SpellViewHolder> {

    private LayoutInflater inflater;
    private Context context;

    public SpellSlotExpandableListAdapter(Context context, List<? extends ParentListItem> itemList) {
        super(itemList);
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public SpellSlotViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = inflater.inflate(R.layout.adapter_item_spell_slot, parentViewGroup, false);
        return new SpellSlotViewHolder(view);
    }

    @Override
    public SpellViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = inflater.inflate(R.layout.adapter_item_spell, childViewGroup, false);
        return new SpellViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(SpellSlotViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        SpellSlot spellSlot = (SpellSlot) parentListItem;
        parentViewHolder.bind(spellSlot, context);
    }

    @Override
    public void onBindChildViewHolder(SpellViewHolder childViewHolder, int position, Object childListItem) {
        Spell spell = (Spell) childListItem;
        childViewHolder.bind(spell, context);
    }
}
