package com.boredombabies.charactersheet.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.helper.EditTextTextWatcher;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;

import io.realm.Realm;

/**
 * Created by mark.knutson on 1/1/16.
 */
public class FeaturesFragment extends android.support.v4.app.Fragment {
    Realm realm;
    PlayerCharacter playerCharacter;

    public FeaturesFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        realm = Realm.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_features, container, false);

        EditText personalityTraits = (EditText) rootView.findViewById(R.id.personalityTraits);
        personalityTraits.setText(playerCharacter.getFeatures().getPersonalityTraits());
        personalityTraits.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getFeatures().setPersonalityTraits(s.toString());
            }
        });
        // TODO: move languages to features model
        EditText otherProfAndLang = (EditText) rootView.findViewById(R.id.languages);
        otherProfAndLang.setText(playerCharacter.getAttributes().getOtherProficienciesAndLanguages());
        otherProfAndLang.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getAttributes().setOtherProficienciesAndLanguages(s.toString());
            }
        });
        EditText ideals = (EditText) rootView.findViewById(R.id.ideals);
        ideals.setText(playerCharacter.getFeatures().getIdeals());
        ideals.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getFeatures().setIdeals(s.toString());
            }
        });
        EditText bonds = (EditText) rootView.findViewById(R.id.bonds);
        bonds.setText(playerCharacter.getFeatures().getBonds());
        bonds.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getFeatures().setBonds(s.toString());
            }
        });
        EditText flaws = (EditText) rootView.findViewById(R.id.flaws);
        flaws.setText(playerCharacter.getFeatures().getFlaws());
        flaws.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getFeatures().setFlaws(s.toString());
            }
        });
        EditText features = (EditText) rootView.findViewById(R.id.features);
        features.setText(playerCharacter.getFeatures().getFeatures());
        features.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getFeatures().setFeatures(s.toString());
            }
        });

        return rootView;
    }
}
