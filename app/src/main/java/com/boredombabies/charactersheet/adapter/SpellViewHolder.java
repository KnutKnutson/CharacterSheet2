package com.boredombabies.charactersheet.adapter;

import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.db.RealmHelper;
import com.boredombabies.charactersheet.helper.EditTextTextWatcher;
import com.boredombabies.charactersheet.model.Spell;

import io.realm.Realm;

/**
 * Created on 1/2/16.
 */
public class SpellViewHolder extends ChildViewHolder {

    CheckBox spellPrepared;
    TextView spellName;
    EditTextTextWatcher textWatcher;

    public SpellViewHolder(View itemView) {
        super(itemView);

        spellPrepared = (CheckBox) itemView.findViewById(R.id.spellPrepared);
        spellPrepared.setId(View.generateViewId());
        spellName = (TextView) itemView.findViewById(R.id.spellName);
        spellName.setId(View.generateViewId());
    }

    public void bind(final Spell spell, final Context context) {
        spellName.removeTextChangedListener(textWatcher);
        spellName.setText(spell.getName());
        textWatcher = new EditTextTextWatcher(context) {
            @Override
            public void inTransactionCallback(Editable s) {
                spell.setName(s.toString());
            }
        };
        spellName.addTextChangedListener(textWatcher);

        spellPrepared.setOnCheckedChangeListener(null);
        spellPrepared.setChecked(spell.isPrepared());
        spellPrepared.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Realm realm = RealmHelper.getRealm(context);
                realm.beginTransaction();
                spell.setPrepared(isChecked);
                realm.commitTransaction();
            }
        });
    }
}
