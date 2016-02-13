package com.boredombabies.charactersheet.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
public class AlliesFragment extends ListFragment
                            implements AllySelectDialogFragment.AllySelectListener {
    Realm realm;
    PlayerCharacter playerCharacter;
    private CharacterListAdapter listAdapter;

    // Tracks current menu item
    private int allyToRemoveFromParty;
    // Tracks current contextual action mode
    private ActionMode currentActionMode;

    public AlliesFragment() { }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentActionMode != null) { return false; }
                allyToRemoveFromParty = position;
                currentActionMode = getActivity().startActionMode(modeCallBack);
                view.setSelected(true);
                return true;
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        realm = Realm.getInstance(getActivity());
        listAdapter = new CharacterListAdapter(getActivity(), playerCharacter.getAllies().getPlayerCharacterAllies());
        setListAdapter(listAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_allies, container, false);

//        ListView pcAllies = (ListView) rootView.findViewById(R.id.allies_list);
//        pcAllies.setAdapter(listAdapter);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.allies_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newAlly();
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
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        PlayerCharacterHelper.setActiveCharacter(PlayerCharacterHelper.assembleParty(realm).get(position));

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
//        mCallbacks.onItemSelected(PlayerCharacterHelper
//                .assembleParty(realm)
//                .get(position)
//                .getName());
    }

    @Override
    public void onSelectNewAlly(PlayerCharacter newAlly) {
        //TODO add ally to list of allies
        //playerCharacter.getAllies().getPlayerCharacterAllies().add( ally );
        //notify data set changed?
    }

    public void newAlly() {
        DialogFragment allySelectDialogFragment = new AllySelectDialogFragment();
        allySelectDialogFragment.show(getActivity().getSupportFragmentManager(), "newAlly");
    }

// TODO: edit action menu
    private ActionMode.Callback modeCallBack = new ActionMode.Callback() {
        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Remove Ally");
            mode.getMenuInflater().inflate(R.menu.actions_list_item, menu);
            return true;
        }

        // Called each time the action mode is shown.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_delete:
                    final PlayerCharacter honoredDead = PlayerCharacterHelper.getCharacter(realm, allyToRemoveFromParty);
                    PlayerCharacterHelper.killCharacter(realm, honoredDead);
                    listAdapter.notifyDataSetChanged();
                    Snackbar.make(getView(), "Ally Removed From Party", Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    PlayerCharacterHelper.resurrectCharacter(realm, honoredDead);
                                    listAdapter.notifyDataSetChanged();
                                }
                            }).show();
                    mode.finish(); // Action picked, so close the contextual menu
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            currentActionMode = null; // Clear current action mode
        }
    };
}
