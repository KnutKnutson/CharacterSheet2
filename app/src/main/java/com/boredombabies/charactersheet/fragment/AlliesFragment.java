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
public class AlliesFragment extends android.support.v4.app.Fragment {
    Realm realm;
    PlayerCharacter playerCharacter;

    public AlliesFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        realm = Realm.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_equipment, container, false);

        // Top Row
        EditText armorClass = (EditText) rootView.findViewById(R.id.armorClass);
        armorClass.setText(playerCharacter.getCombatStats().getArmorClass());
        armorClass.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getCombatStats().setArmorClass(s.toString());
            }
        });

        return rootView;
    }
}
