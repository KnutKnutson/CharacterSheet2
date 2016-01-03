package com.boredombabies.charactersheet.adapter;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.boredombabies.charactersheet.R;

/**
 * Created on 1/2/16.
 */
public class SpellViewHolder extends ChildViewHolder {
    TextView spellName;

    public SpellViewHolder(View itemView) {
        super(itemView);

        spellName = (TextView) itemView.findViewById(R.id.spellName);
    }
}
