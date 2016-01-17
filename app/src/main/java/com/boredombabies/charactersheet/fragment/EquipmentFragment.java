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

public class EquipmentFragment extends android.support.v4.app.Fragment {
    Realm realm;
    PlayerCharacter playerCharacter;

    public EquipmentFragment() { }

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

        // Money
        EditText copper = (EditText) rootView.findViewById(R.id.copper);
        copper.setText(playerCharacter.getEquipment().getCopper());
        copper.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getEquipment().setCopper(s.toString());
            }
        });
        EditText silver = (EditText) rootView.findViewById(R.id.silver);
        silver.setText(playerCharacter.getEquipment().getSilver());
        silver.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getEquipment().setSilver(s.toString());
            }
        });
        EditText electrum = (EditText) rootView.findViewById(R.id.electrum);
        electrum.setText(playerCharacter.getEquipment().getElectrum());
        electrum.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getEquipment().setElectrum(s.toString());
            }
        });
        EditText gold = (EditText) rootView.findViewById(R.id.gold);
        gold.setText(playerCharacter.getEquipment().getGold());
        gold.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getEquipment().setGold(s.toString());
            }
        });
        EditText platinum = (EditText) rootView.findViewById(R.id.platinum);
        platinum.setText(playerCharacter.getEquipment().getPlatinum());
        platinum.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getEquipment().setPlatinum(s.toString());
            }
        });

        // Equipment
        EditText equipment = (EditText) rootView.findViewById(R.id.equipment);
        equipment.setText(playerCharacter.getEquipment().getEquipment());
        equipment.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getEquipment().setEquipment(s.toString());
            }
        });

        // Treasure
        EditText treasure = (EditText) rootView.findViewById(R.id.treasure);
        treasure.setText(playerCharacter.getEquipment().getTreasure());
        treasure.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getEquipment().setTreasure(s.toString());
            }
        });

        return rootView;
    }
}
