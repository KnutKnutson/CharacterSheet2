package com.boredombabies.charactersheet.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.db.RealmHelper;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.Weapon;
import com.boredombabies.charactersheet.model.builder.WeaponBuilder;

import java.util.List;

import io.realm.Realm;

/**
 * Created on 3/11/16.
 */
public class WeaponViewDialog extends DialogFragment {

    private Weapon weapon;

    public static WeaponViewDialog newInstance(Weapon weapon) {
        WeaponViewDialog wvd = new WeaponViewDialog();
        wvd.setWeapon(weapon);
        return wvd;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View rootView = inflater.inflate(R.layout.dialog_show_weapon, null);
        TextView name = (TextView) rootView.findViewById(R.id.weaponName);
        name.setText(weapon.getName());

        builder.setView(rootView);
        return builder.create();
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
