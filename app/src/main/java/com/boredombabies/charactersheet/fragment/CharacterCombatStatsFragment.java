package com.boredombabies.charactersheet.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.helper.Constants;
import com.boredombabies.charactersheet.helper.EditTextTextWatcher;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.interfaces.CharacterSheetFragmentCallbacks;
import com.boredombabies.charactersheet.model.Attack;
import com.boredombabies.charactersheet.model.PlayerCharacter;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
//TODO: remove debug log lines
public class CharacterCombatStatsFragment extends Fragment {
    Realm realm;
    PlayerCharacter playerCharacter;
    CharacterSheetFragmentCallbacks callbacks;
    int viewPagerPreferencesNumber;

    public CharacterCombatStatsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.i("on create", "creating it");
//        if (savedInstanceState != null) {
//            for (String key : savedInstanceState.keySet()) {
//                Object value = savedInstanceState.get(key);
//                Log.d("SAVEDSTATE", String.format("%s %s (%s)", key,
//                        value.toString(), value.getClass().getName()));
//            }
//        }
        playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        realm = Realm.getInstance(getActivity());

        Bundle parentBundle = this.getParentFragment().getArguments();
        if (parentBundle != null) {
            this.viewPagerPreferencesNumber = parentBundle.getInt(Constants.VIEW_PAGER_PREF_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character_combat_stats, container, false);

        // Top Row
        EditText armorClass = (EditText) rootView.findViewById(R.id.armorClass);
        armorClass.setText(playerCharacter.getCombatStats().getArmorClass());
        armorClass.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getCombatStats().setArmorClass(s.toString());
            }
        });
        EditText initiative = (EditText) rootView.findViewById(R.id.initiative);
        initiative.setText(playerCharacter.getCombatStats().getInitiative());
        initiative.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getCombatStats().setInitiative(s.toString());
            }
        });
        EditText speed = (EditText) rootView.findViewById(R.id.speed);
        speed.setText(playerCharacter.getCombatStats().getSpeed());
        speed.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getCombatStats().setSpeed(s.toString());
            }
        });

        // Hit Dice
        EditText hitDieTotalValue = (EditText) rootView.findViewById(R.id.hitDieTotalValue);
        hitDieTotalValue.setText(playerCharacter.getCombatStats().getHitDiceTotal());
        hitDieTotalValue.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getCombatStats().setHitDiceTotal(s.toString());
            }
        });
        EditText hitDiceValue = (EditText) rootView.findViewById(R.id.hitDiceValue);
        hitDiceValue.setText(playerCharacter.getCombatStats().getHitDice());
        hitDiceValue.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getCombatStats().setHitDice(s.toString());
            }
        });

        // Death Saves
        CheckBox success0 = (CheckBox) rootView.findViewById(R.id.success0);
        success0.setChecked(playerCharacter.getCombatStats().isDeathSaveSuccess0());
        success0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Realm realm = Realm.getInstance(getActivity());
                realm.beginTransaction();
                playerCharacter.getCombatStats().setDeathSaveSuccess0(isChecked);
                realm.commitTransaction();
            }
        });
        CheckBox success1 = (CheckBox) rootView.findViewById(R.id.success1);
        success1.setChecked(playerCharacter.getCombatStats().isDeathSaveSuccess1());
        success1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Realm realm = Realm.getInstance(getActivity());
                realm.beginTransaction();
                playerCharacter.getCombatStats().setDeathSaveSuccess1(isChecked);
                realm.commitTransaction();
            }
        });
        CheckBox success2 = (CheckBox) rootView.findViewById(R.id.success2);
        success2.setChecked(playerCharacter.getCombatStats().isDeathSaveSuccess2());
        success2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Realm realm = Realm.getInstance(getActivity());
                realm.beginTransaction();
                playerCharacter.getCombatStats().setDeathSaveSuccess2(isChecked);
                realm.commitTransaction();
            }
        });
        CheckBox failure0 = (CheckBox) rootView.findViewById(R.id.failure0);
        failure0.setChecked(playerCharacter.getCombatStats().isDeathSaveFailure0());
        failure0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Realm realm = Realm.getInstance(getActivity());
                realm.beginTransaction();
                playerCharacter.getCombatStats().setDeathSaveFailure0(isChecked);
                realm.commitTransaction();
            }
        });
        CheckBox failure1 = (CheckBox) rootView.findViewById(R.id.failure1);
        failure1.setChecked(playerCharacter.getCombatStats().isDeathSaveFailure1());
        failure1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Realm realm = Realm.getInstance(getActivity());
                realm.beginTransaction();
                playerCharacter.getCombatStats().setDeathSaveFailure1(isChecked);
                realm.commitTransaction();
            }
        });
        CheckBox failure2 = (CheckBox) rootView.findViewById(R.id.failure2);
        failure2.setChecked(playerCharacter.getCombatStats().isDeathSaveFailure2());
        failure2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Realm realm = Realm.getInstance(getActivity());
                realm.beginTransaction();
                playerCharacter.getCombatStats().setDeathSaveFailure2(isChecked);
                realm.commitTransaction();
            }
        });

        // Attacks
        LinearLayout attacksAndSpellcastingLayout = (LinearLayout) rootView.findViewById(R.id.attacksAndSpellcastingLayout);

        for (Attack attack : playerCharacter.getCombatStats().getAttacks()) {
            View attackComponent = inflater.inflate(R.layout.component_attack, container, false);
            final Attack thisAttack = attack;

            EditText attackName = (EditText) attackComponent.findViewById(R.id.attackName);
            attackName.setId(View.generateViewId());
            attackName.setText(thisAttack.getName());
            attackName.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
                @Override
                public void inTransactionCallback(Editable s) {
                    thisAttack.setName(s.toString());
                }
            });
            EditText attackBonus = (EditText) attackComponent.findViewById(R.id.attackBonus);
            attackBonus.setId(View.generateViewId());
            attackBonus.setText(thisAttack.getAttackBonus());
            attackBonus.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
                @Override
                public void inTransactionCallback(Editable s) {
                    thisAttack.setAttackBonus(s.toString());
                }
            });
            EditText attackDamage = (EditText) attackComponent.findViewById(R.id.attackDamageAndType);
            attackDamage.setId(View.generateViewId());
            attackDamage.setText(thisAttack.getDamageAndType());
            attackDamage.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
                @Override
                public void inTransactionCallback(Editable s) {
                    thisAttack.setDamageAndType(s.toString());
                }
            });

            attacksAndSpellcastingLayout.addView(attackComponent);
        }

        // Attack Notes
        EditText attackNotes = (EditText) rootView.findViewById(R.id.attacksAndSpellCastingNotes);
        attackNotes.setText(playerCharacter.getCombatStats().getAttackAndSpellNotes());
        attackNotes.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getCombatStats().setAttackAndSpellNotes(s.toString());
            }
        });

        return rootView;
    }
}
