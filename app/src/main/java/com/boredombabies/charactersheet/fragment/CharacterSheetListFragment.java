package com.boredombabies.charactersheet.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.adapter.CharacterListAdapter;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;

import io.realm.Realm;

/**
 * A list fragment representing a list of CharacterSheets. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link CharacterProfileFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
// TODO: This fragment is getting out of hand.
public class CharacterSheetListFragment extends ListFragment {

    private Realm realm;
    private CharacterListAdapter listAdapter;

    // Tracks current menu item
    private int characterMenuItem;
    // Tracks current contextual action mode
    private ActionMode currentActionMode;

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        void onItemSelected(String id);
        void onCharacterToShare(int playerCharacter);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) { }
        @Override
        public void onCharacterToShare(int playerCharacter) { }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharacterSheetListFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentActionMode != null) {
                    return false;
                }
                characterMenuItem = position;
                currentActionMode = getActivity().startActionMode(modeCallBack);
                view.setSelected(true);
                return true;
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getInstance(getActivity());
        listAdapter = new CharacterListAdapter(getActivity(), PlayerCharacterHelper.assembleParty(realm));
        setListAdapter(listAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        PlayerCharacterHelper.setActiveCharacter(PlayerCharacterHelper.assembleParty(realm).get(position));

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(PlayerCharacterHelper
                .assembleParty(realm)
                .get(position)
                .getName());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //realm.close();
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    public CharacterListAdapter getListAdapter() {
        return listAdapter;
    }

    private ActionMode.Callback modeCallBack = new ActionMode.Callback() {
        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Character Options");
            mode.getMenuInflater().inflate(R.menu.actions_character_list_item, menu);
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
                    deleteCharacter();
                    mode.finish(); // Action picked, so close the contextual menu
                    return true;
                case R.id.menu_share:
                    mCallbacks.onCharacterToShare(characterMenuItem);
                    mode.finish();
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

    private void deleteCharacter() {
        final PlayerCharacter honoredDead = realm.copyFromRealm(PlayerCharacterHelper.getCharacter(realm, characterMenuItem));
        // TODO: try copyFromRealm then delete... though that may change the id...
        PlayerCharacterHelper.killCharacter(realm, PlayerCharacterHelper.getCharacter(realm, characterMenuItem));
        listAdapter.notifyDataSetChanged();
        Snackbar.make(getView(), "Character Deleted", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //PlayerCharacterHelper.resurrectCharacter(realm, honoredDead);
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(honoredDead);
                        realm.commitTransaction();
                        listAdapter.notifyDataSetChanged();
                    }
                }).show();
    }
}
