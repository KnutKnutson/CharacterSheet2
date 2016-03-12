package com.boredombabies.charactersheet.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.adapter.WeaponListAdapter;
import com.boredombabies.charactersheet.db.RealmHelper;
import com.boredombabies.charactersheet.dialog.WeaponSelectDialog;
import com.boredombabies.charactersheet.dialog.WeaponViewDialog;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.model.Weapon;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;


public class WeaponListFragment extends ItemListFragment {
    Realm realm;
    PlayerCharacter playerCharacter;
    List<Weapon> weapons;
    private WeaponListAdapter listAdapter;

    public WeaponListFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        realm = RealmHelper.getRealm(getActivity());
        RealmChangeListener listener = new RealmChangeListener() {
            public void onChange() {
                if (listAdapter != null) {
                    listAdapter.notifyDataSetChanged();
                }
            }
        };
        realm.addChangeListener(listener);
        weapons = playerCharacter.getEquipment().getWeapons();
        listAdapter = new WeaponListAdapter(getActivity(), weapons);
        setListAdapter(listAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_list, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.items_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newWeapon();
            }
        });

        TextView label = (TextView) rootView.findViewById(R.id.label_items);
        label.setText(R.string.label_weapons);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        viewWeapon(weapons.get(position));
    }

    public void newWeapon() {
        WeaponSelectDialog weaponSelect = new WeaponSelectDialog();
        weaponSelect.setTargetFragment(this, NEW_ITEM_REQUEST_CODE);
        weaponSelect.show(getActivity().getSupportFragmentManager(), "newWeapon");
    }

    public void viewWeapon(Weapon weapon) {
        WeaponViewDialog weaponShow = WeaponViewDialog.newInstance(weapon);
        weaponShow.setTargetFragment(this, NEW_ITEM_REQUEST_CODE);
        weaponShow.show(getActivity().getSupportFragmentManager(), "viewWeapon");
    }

    @Override
    public ActionMode.Callback getActionModeCallback() {
        return new ActionMode.Callback() {
            // Called when the action mode is created; startActionMode() was called
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.setTitle("Remove Weapon");
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
                        final Weapon brokenWeapon = weapons.get(getItemToRemove());
                        realm.beginTransaction();
                        playerCharacter.getEquipment().getWeapons().remove(brokenWeapon);
                        realm.commitTransaction();
//                        listAdapter.notifyDataSetChanged();
                        setListViewHeightBasedOnItems(getListView());

                        Snackbar.make(getView(), "Weapon Removed From Equipment", Snackbar.LENGTH_LONG)
                                .setAction("Undo", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        realm.beginTransaction();
                                        playerCharacter.getEquipment().getWeapons().add(brokenWeapon);
                                        realm.commitTransaction();
//                                        listAdapter.notifyDataSetChanged();
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
                setCurrentActionMode(null); // Clear current action mode
            }
        };
    }


}
