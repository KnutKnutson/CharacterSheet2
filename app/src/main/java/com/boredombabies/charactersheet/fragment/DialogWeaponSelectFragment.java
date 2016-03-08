package com.boredombabies.charactersheet.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.adapter.CharacterListAdapter;
import com.boredombabies.charactersheet.adapter.WeaponListAdapter;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.helper.WeaponComparator;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.model.Weapon;
import com.boredombabies.charactersheet.model.builder.WeaponBuilder;

import java.util.Collections;
import java.util.List;

import io.realm.Realm;

/**
 * Created on 3/6/16.
 * TODO: IMPLEMENT
 */
public class DialogWeaponSelectFragment extends DialogFragment {

    Realm realm;
    private ArrayAdapter listAdapter;
    private List<Weapon> weapons;
    private String[] weaponsSimpleList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        realm = Realm.getInstance(activity);
        weaponsSimpleList = WeaponBuilder.WEAPONS;
        //Collections.sort(weapons, new WeaponComparator());
        //listAdapter = new WeaponListAdapter(activity, weapons);
        listAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, weaponsSimpleList);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setTitle("Choose Weapon")
                .setAdapter(listAdapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        saveWeapon(weaponsSimpleList[which]);
                    }
                });
        return builder.create();
    }

    private void saveWeapon(String weaponName) {
        Weapon weapon = realm.where(Weapon.class).equalTo("name", weaponName).findFirst();
        if (weapon == null) {
            weapon = WeaponBuilder.build(weaponName);
        }
        Realm realm = Realm.getInstance(getActivity());
        realm.beginTransaction();
        Weapon realmWeapon = realm.copyToRealm(weapon);
        //PlayerCharacterHelper.getActiveCharacter().getEquipment().().add(realmWeapon);
        realm.commitTransaction();
    }
}
