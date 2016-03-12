package com.boredombabies.charactersheet.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.db.RealmHelper;
import com.boredombabies.charactersheet.model.Weapon;

import io.realm.Realm;

/**
 * Created on 3/11/16.
 * TODO: add text change listeners
 */
public class WeaponShowDialog extends DialogFragment {

    private Weapon weapon;
    public Realm realm;

    public static WeaponShowDialog newInstance(Weapon weapon) {
        WeaponShowDialog wvd = new WeaponShowDialog();
        wvd.setWeapon(weapon);
        wvd.realm = RealmHelper.getRealm(wvd.getActivity());
        return wvd;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View rootView = inflater.inflate(R.layout.dialog_show_weapon, null);
        TextView name = (TextView) rootView.findViewById(R.id.weaponName);
        name.setText(weapon.getName());

//        if (weapon.getName().equals("Club")) {
//            TextView rname = (TextView) rootView.findViewById(R.id.resourceName);
//            rname.setText(weapon.getResourceName());
//        }

        TextView proficiency = (TextView) rootView.findViewById(R.id.weaponProficiency);
        proficiency.setText(weapon.getProficiency());

        EditText cost = (EditText) rootView.findViewById(R.id.weaponCost);
        cost.setText(weapon.getCost());
        EditText weight = (EditText) rootView.findViewById(R.id.weaponWeight);
        weight.setText(weapon.getWeight());
        EditText damage = (EditText) rootView.findViewById(R.id.weaponDamage);
        damage.setText(weapon.getDamage() + " " + weapon.getDamageType());
        //TODO: multiline properties
        EditText properties = (EditText) rootView.findViewById(R.id.weaponProperties);
        properties.setText(weapon.getProperties());
        EditText notes = (EditText) rootView.findViewById(R.id.weaponNotes);
        notes.setText(weapon.getNotes());

        builder.setView(rootView);
        return builder.create();
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
