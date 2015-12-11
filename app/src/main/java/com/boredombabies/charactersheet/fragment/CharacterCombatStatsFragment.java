package com.boredombabies.charactersheet.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.helper.Constants;
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
    private int viewPagerPreferencesNumber;

    public CharacterCombatStatsFragment() { }

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

        return rootView;
    }
}
