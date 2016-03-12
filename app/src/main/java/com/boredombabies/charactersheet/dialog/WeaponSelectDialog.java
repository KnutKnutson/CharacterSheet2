package com.boredombabies.charactersheet.dialog;

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
import com.boredombabies.charactersheet.db.RealmHelper;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.helper.WeaponComparator;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.model.Weapon;
import com.boredombabies.charactersheet.model.builder.WeaponBuilder;

import java.util.Collections;
import java.util.List;

import io.realm.Realm;


public class WeaponSelectDialog extends DialogFragment {

    Realm realm;
    private ArrayAdapter listAdapter;
    private List<Weapon> weapons;
    private String[] weaponsSimpleList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        realm = RealmHelper.getRealm(activity);
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
                        // nonzero in resultcode
                        getTargetFragment().onActivityResult(getTargetRequestCode(), 42, null);
                    }
                });
        return builder.create();
    }

    private void saveWeapon(String weaponName) {
        Weapon weapon = realm.where(Weapon.class).equalTo("name", weaponName).findFirst();
        if (weapon == null) {
            weapon = WeaponBuilder.build(weaponName);
        }
        realm.beginTransaction();
        Weapon realmWeapon = realm.copyToRealm(weapon);
        PlayerCharacterHelper.getActiveCharacter().getEquipment().getWeapons().add(realmWeapon);
        realm.commitTransaction();
    }
}
