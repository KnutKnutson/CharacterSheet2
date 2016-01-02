package com.boredombabies.charactersheet.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.boredombabies.charactersheet.R;

/**
 * Created on 1/2/16.
 */
public class SpellSlotViewHolder extends ParentViewHolder {
    public TextView mCrimeTitleTextView;
    public ImageButton mParentDropDownArrow;

    public SpellSlotViewHolder(View itemView) {
        super(itemView);

        //mCrimeTitleTextView = (TextView) itemView.findViewById(R.id.parent_list_item_crime_title_text_view);
        //mParentDropDownArrow = (ImageButton) itemView.findViewById(R.id.parent_list_item_expand_arrow);
    }
}
