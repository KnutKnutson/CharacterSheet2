package com.boredombabies.charactersheet.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.boredombabies.charactersheet.activity.CharacterSheetViewPagerActivity;
import com.boredombabies.charactersheet.adapter.CharacterListAdapter;
import com.boredombabies.charactersheet.db.RealmHelper;
import com.boredombabies.charactersheet.helper.EditTextTextWatcher;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;

import java.util.List;

import io.realm.Realm;

public class AlliesFragment extends ItemListFragment {
    Realm realm;
    PlayerCharacter playerCharacter;
    List<PlayerCharacter> allies;
    private CharacterListAdapter listAdapter;

    // Tracks current menu item
    private int allyToRemoveFromParty;
    // Tracks current contextual action mode
    private ActionMode currentActionMode;

    public AlliesFragment() { }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        ListView listView = getListView();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentActionMode != null) {
                    return false;
                }
                allyToRemoveFromParty = position;
                currentActionMode = getActivity().startActionMode(modeCallBack);
                view.setSelected(true);
                return true;
            }
        });

        setListViewHeightBasedOnItems(listView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        realm = RealmHelper.getRealm(getActivity());
        allies = playerCharacter.getAllies().getPlayerCharacterAllies();
        listAdapter = new CharacterListAdapter(getActivity(), allies);
        setListAdapter(listAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_allies, container, false);

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

        PlayerCharacterHelper.setActiveCharacter( allies.get(position) );

        Intent viewAlly = new Intent(getActivity(), CharacterSheetViewPagerActivity.class);
        startActivity(viewAlly);
    }

    public void newAlly() {
        AllySelectDialogFragment allySelectDialogFragment = new AllySelectDialogFragment();
        allySelectDialogFragment.setCurrentAlliesListAdapter(listAdapter);
        allySelectDialogFragment.setTargetFragment(this, NEW_ITEM_REQUEST_CODE);
        allySelectDialogFragment.show(getActivity().getSupportFragmentManager(), "newAlly");
    }

    private ActionMode.Callback modeCallBack = new ActionMode.Callback() {
        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Remove Ally");
            mode.getMenuInflater().inflate(R.menu.actions_allies_list_item, menu);
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
                    final PlayerCharacter traitor = allies.get(allyToRemoveFromParty);
                    realm.beginTransaction();
                    playerCharacter.getAllies().getPlayerCharacterAllies().remove(traitor);
                    realm.commitTransaction();
                    listAdapter.notifyDataSetChanged();
                    setListViewHeightBasedOnItems(getListView());

                    Snackbar.make(getView(), "Ally Removed From Party", Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    realm.beginTransaction();
                                    playerCharacter.getAllies().getPlayerCharacterAllies().add(traitor);
                                    realm.commitTransaction();
                                    listAdapter.notifyDataSetChanged();
                                    setListViewHeightBasedOnItems(getListView());
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
