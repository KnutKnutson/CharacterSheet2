package com.boredombabies.charactersheet.adapter;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.helper.EditTextTextWatcher;
import com.boredombabies.charactersheet.model.SpellSlot;

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
    EditTextTextWatcher totalWatcher;
    EditTextTextWatcher expendedWatcher;

    public SpellSlotViewHolder(View itemView) {
        super(itemView);

        spellSlotTriangle = (ImageView) itemView.findViewById(R.id.spellSlotTriangle);
        spellSlotLevel = (TextView) itemView.findViewById(R.id.spellSlotLevel);
        slotsTotal = (EditText) itemView.findViewById(R.id.slotsTotal);
        slotsTotal.setId(View.generateViewId());
        slotsExpended = (EditText) itemView.findViewById(R.id.slotsExpended);
        slotsExpended.setId(View.generateViewId());
    }

    public void bind(final SpellSlot spellSlot, Context context) {
        spellSlotLevel.setText(spellSlot.getLevel());

        slotsTotal.removeTextChangedListener(totalWatcher);
        slotsTotal.setText(spellSlot.getSlotsTotal());
        totalWatcher = new EditTextTextWatcher(context) {
            @Override
            public void inTransactionCallback(Editable s) {
                spellSlot.setSlotsTotal(s.toString());
            }
        };
        slotsTotal.addTextChangedListener(totalWatcher);

        slotsExpended.removeTextChangedListener(expendedWatcher);
        slotsExpended.setText(spellSlot.getSlotsExpended());
        expendedWatcher = new EditTextTextWatcher(context) {
            @Override
            public void inTransactionCallback(Editable s) {
                spellSlot.setSlotsExpended(s.toString());
            }
        };
        slotsExpended.addTextChangedListener(expendedWatcher);
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
                        .setDuration(DEFAULT_ROTATE_DURATION_MS)
        );
        set.start();
    }

    private void contract() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(spellSlotTriangle, "rotation", ROTATED_POSITION, INITIAL_POSITION)
                        .setDuration(DEFAULT_ROTATE_DURATION_MS)
        );
        set.start();
    }
}
