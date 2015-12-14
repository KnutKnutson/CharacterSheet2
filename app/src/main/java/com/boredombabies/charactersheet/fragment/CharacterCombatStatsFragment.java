package com.boredombabies.charactersheet.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.helper.Constants;
import com.boredombabies.charactersheet.helper.EditTextTextWatcher;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.interfaces.CharacterSheetFragmentCallbacks;
import com.boredombabies.charactersheet.model.PlayerCharacter;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterCombatStatsFragment extends Fragment {
    Realm realm;
    PlayerCharacter playerCharacter;
    CharacterSheetFragmentCallbacks callbacks;

    public CharacterCombatStatsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        realm = Realm.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character_combat_stats, container, false);

        EditText inspiration = (EditText) rootView.findViewById(R.id.inspiration);
        inspiration.setText(Integer.toString(playerCharacter.getAttributes().getInspiration()));
        inspiration.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                int insp = (s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString()));
                playerCharacter.getAttributes().setInspiration(insp);
            }
        });

        return rootView;
    }
}
