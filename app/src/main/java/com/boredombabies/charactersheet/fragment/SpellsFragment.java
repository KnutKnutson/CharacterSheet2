package com.boredombabies.charactersheet.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.adapter.SpellSlotExpandableListAdapter;
import com.boredombabies.charactersheet.helper.EditTextTextWatcher;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;

import io.realm.Realm;

/**
 * Created by mark.knutson on 1/1/16.
 */
public class SpellsFragment extends Fragment {
    Realm realm;
    PlayerCharacter playerCharacter;
    RecyclerView spellSlotRecyclerView;

    public SpellsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        realm = Realm.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_spells, container, false);

        // Top Row
        EditText spellCastingAbility = (EditText) rootView.findViewById(R.id.spellCastingAbility);
        spellCastingAbility.setText(playerCharacter.getSpellCasting().getSpellCastingAbility());
        spellCastingAbility.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getSpellCasting().setSpellCastingAbility(s.toString());
            }
        });

        spellSlotRecyclerView = (RecyclerView) rootView.findViewById(R.id.spellSlotsExpListView);

        // Spell Slots List
        SpellSlotExpandableListAdapter expandableAdapter =
                new SpellSlotExpandableListAdapter(
                        getActivity(),
                        playerCharacter.getSpellCasting().getSpellSlots()
                );
        expandableAdapter.onRestoreInstanceState(savedInstanceState);
        spellSlotRecyclerView.setAdapter(expandableAdapter);
        spellSlotRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((SpellSlotExpandableListAdapter) spellSlotRecyclerView.getAdapter()).onSaveInstanceState(outState);
    }
}
