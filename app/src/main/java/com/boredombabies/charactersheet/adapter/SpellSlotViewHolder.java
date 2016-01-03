package com.boredombabies.charactersheet.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.boredombabies.charactersheet.R;

/**
 * Created on 1/2/16.
 */
public class SpellSlotViewHolder extends ParentViewHolder {
    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 90f;
    private static final float PIVOT_VALUE = 0.5f;
    private static final long DEFAULT_ROTATE_DURATION_MS = 200;
    private static final boolean HONEYCOMB_AND_ABOVE = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;

    public ImageView spellSlotTriangle;
    public TextView spellSlotLevel;
    public EditText slotsTotal;
    public EditText slotsExpended;

    public SpellSlotViewHolder(View itemView) {
        super(itemView);

        spellSlotTriangle = (ImageView) itemView.findViewById(R.id.spellSlotTriangle);
        spellSlotLevel = (TextView) itemView.findViewById(R.id.spellSlotLevel);
        slotsTotal = (EditText) itemView.findViewById(R.id.slotsTotal);
        slotsExpended = (EditText) itemView.findViewById(R.id.slotsExpended);
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        // initiallyExpanded set in SpellSlot model

        if (!HONEYCOMB_AND_ABOVE) {
            return;
        }
        if (expanded) {
            contract();
        } else {
            expand();
        }
    }

    private void expand() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(spellSlotTriangle, "rotation", INITIAL_POSITION, ROTATED_POSITION)
                        .setDuration(DEFAULT_ROTATE_DURATION_MS),
                ObjectAnimator.ofFloat(spellSlotLevel, "translationX", 55.0f)
                        .setDuration(DEFAULT_ROTATE_DURATION_MS),
                ObjectAnimator.ofFloat(spellSlotLevel, "translationY", -45.0f)
                        .setDuration(DEFAULT_ROTATE_DURATION_MS)
        );
        set.start();
    }

    private void contract() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(spellSlotTriangle, "rotation", ROTATED_POSITION, INITIAL_POSITION)
                        .setDuration(DEFAULT_ROTATE_DURATION_MS),
                ObjectAnimator.ofFloat(spellSlotLevel, "translationX", 10.0f)
                        .setDuration(DEFAULT_ROTATE_DURATION_MS),
                ObjectAnimator.ofFloat(spellSlotLevel, "translationY", 0.0f)
                        .setDuration(DEFAULT_ROTATE_DURATION_MS)
        );
        set.start();
    }
}
