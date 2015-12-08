package com.boredombabies.charactersheet.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
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
import com.boredombabies.charactersheet.model.AbilityScore;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.model.Skill;

import io.realm.Realm;

/**
 * Created by mark.knutson on 11/28/15.
 */
public class CharacterAttributesFragment extends Fragment {
    Realm realm;
    PlayerCharacter playerCharacter;
    CharacterSheetFragmentCallbacks callbacks;
    private int viewPagerPreferencesNumber;

    public CharacterAttributesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        View rootView = inflater.inflate(R.layout.fragment_character_attributes, container, false);

        EditText inspiration = (EditText) rootView.findViewById(R.id.inspiration);
        inspiration.setText(Integer.toString(playerCharacter.getAttributes().getInspiration()));
        inspiration.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                int insp = (s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString()));
                playerCharacter.getAttributes().setInspiration(insp);
            }
        });

        EditText profBonus = (EditText) rootView.findViewById(R.id.proficiencyBonus);
        profBonus.setText(Integer.toString(playerCharacter.getAttributes().getProficiencyBonus()));
        profBonus.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                int pb = (s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString()));
                playerCharacter.getAttributes().setProficiencyBonus(pb);
            }
        });

        EditText passiveWis = (EditText) rootView.findViewById(R.id.passiveWisdom);
        passiveWis.setText(Integer.toString(playerCharacter.getAttributes().getPassiveWisdom()));
        passiveWis.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                int pw = (s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString()));
                playerCharacter.getAttributes().setInspiration(pw);
            }
        });

        // Add ability scores
        LinearLayout abilityScoreLayout = (LinearLayout) rootView.findViewById(R.id.ability_scores);
        for (final AbilityScore abilityScore : playerCharacter.getAttributes().getAbilityScores()) {
            View abilityScoreComponent = inflater.inflate(R.layout.component_ability_score, container, false);
            TextView name = (TextView) abilityScoreComponent.findViewById(R.id.ability_score_name);
            final TextView modifier = (TextView) abilityScoreComponent.findViewById(R.id.ability_modifier);
            EditText score = (EditText) abilityScoreComponent.findViewById(R.id.ability_score);
            name.setText(abilityScore.getName());
            modifier.setText(Integer.toString(abilityScore.getAbilityModifier()));
            score.setText(Integer.toString(abilityScore.getAbilityScore()));
            score.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
                @Override
                public void inTransactionCallback(Editable s) {
                    int as = (s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString()));
                    abilityScore.setAbilityScore(as);
                    modifier.setText(Integer.toString(abilityScore.getAbilityModifier()));
                }
            });

            abilityScoreLayout.addView(abilityScoreComponent);
        }

        // Add Saving Throws
        LinearLayout savingThrowsLayout = (LinearLayout) rootView.findViewById(R.id.savingThrows);
        for (final Skill savingThrow : playerCharacter.getAttributes().getSavingThrows()) {
            View savingThrowComponent = inflater.inflate(R.layout.component_skill, container, false);

            CheckBox skillTrained = (CheckBox) savingThrowComponent.findViewById(R.id.skillTrained);
            skillTrained.setChecked(savingThrow.isTrained());
            skillTrained.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Realm realm = Realm.getInstance(getActivity());
                    realm.beginTransaction();
                    savingThrow.setTrained(isChecked);
                    realm.commitTransaction();
                }
            });

            EditText skillBonus = (EditText) savingThrowComponent.findViewById(R.id.skillBonus);
            skillBonus.setText(savingThrow.getSkillBonus());
            skillBonus.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
                @Override
                public void inTransactionCallback(Editable s) {
                    savingThrow.setSkillBonus(s.toString());
                }
            });

            TextView skillName = (TextView) savingThrowComponent.findViewById(R.id.skillName);
            skillName.setText(savingThrow.getSkillName());

            savingThrowsLayout.addView(savingThrowComponent);
        }

        // Add Skills
        LinearLayout skillsLayout = (LinearLayout) rootView.findViewById(R.id.skills);
        for (final Skill savingThrow : playerCharacter.getAttributes().getSkills()) {
            View skillsComponent = inflater.inflate(R.layout.component_skill, container, false);

            CheckBox skillTrained = (CheckBox) skillsComponent.findViewById(R.id.skillTrained);
            skillTrained.setChecked(savingThrow.isTrained());
            skillTrained.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Realm realm = Realm.getInstance(getActivity());
                    realm.beginTransaction();
                    savingThrow.setTrained(isChecked);
                    realm.commitTransaction();
                }
            });

            EditText skillBonus = (EditText) skillsComponent.findViewById(R.id.skillBonus);
            skillBonus.setText(savingThrow.getSkillBonus());
            skillBonus.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
                @Override
                public void inTransactionCallback(Editable s) {
                    savingThrow.setSkillBonus(s.toString());
                }
            });

            TextView skillName = (TextView) skillsComponent.findViewById(R.id.skillName);
            skillName.setText(savingThrow.getSkillName());

            TextView skillModifier = (TextView) skillsComponent.findViewById(R.id.skillModifier);
            String modifier = savingThrow.getSkillAbilityModifier();
            modifier = (TextUtils.isEmpty(modifier) ? "" : "(" + modifier + ")");
            skillModifier.setText((modifier));

            skillsLayout.addView(skillsComponent);
        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Activities containing this fragment must implement its callbacks.
        if (!(context instanceof CharacterSheetFragmentCallbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        callbacks = (CharacterSheetFragmentCallbacks) context;
    }
}
