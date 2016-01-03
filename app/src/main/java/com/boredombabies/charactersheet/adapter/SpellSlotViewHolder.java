package com.boredombabies.charactersheet.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.boredombabies.charactersheet.R;

/**
 * Created on 1/2/16.
 */
public class SpellSlotViewHolder extends ParentViewHolder {
    public TextView spellSlotLevel;

    public SpellSlotViewHolder(View itemView) {
        super(itemView);

        spellSlotLevel = (TextView) itemView.findViewById(R.id.spellSlotLevel);
        Log.d("SpellSlotViewHolder", spellSlotLevel.toString());
    }
}
