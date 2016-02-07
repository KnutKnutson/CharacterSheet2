package com.boredombabies.charactersheet.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.adapter.CharacterListAdapter;
import com.boredombabies.charactersheet.helper.EditTextTextWatcher;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;

import io.realm.Realm;

/**
 * Export allies as list of uuids.
 * On import, find or create allies by uuid.
 * Only show allies where name != null
 */
public class AlliesFragment extends android.support.v4.app.Fragment
                            implements AllySelectDialogFragment.AllySelectListener {
    Realm realm;
    PlayerCharacter playerCharacter;
    private CharacterListAdapter listAdapter;

    public AlliesFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        realm = Realm.getInstance(getActivity());
        listAdapter = new CharacterListAdapter(getActivity(), playerCharacter.getAllies().getPlayerCharacterAllies());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_allies, container, false);

        ListView pcAllies = (ListView) rootView.findViewById(R.id.allies_list);
        pcAllies.setAdapter(listAdapter);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.allies_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAlly();
            }
        });

        EditText allies = (EditText) rootView.findViewById(R.id.allies);
        allies.setText(playerCharacter.getAllies().getAllies());
        allies.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getAllies().setAllies(s.toString());
            }
        });

        EditText organizations = (EditText) rootView.findViewById(R.id.organizations);
        organizations.setText(playerCharacter.getAllies().getOrganizationName());
        organizations.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getAllies().setOrganizationName(s.toString());
            }
        });

        return rootView;
    }

    @Override
    public void onAllySelect(DialogFragment dialog) {
        //TODO add ally to list of allies
    }

    public void selectAlly() {
        DialogFragment allySelectDialogFragment = new AllySelectDialogFragment();
        allySelectDialogFragment.show(getActivity().getSupportFragmentManager(), "allySelect");
    }
}
