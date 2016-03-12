package com.boredombabies.charactersheet.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.adapter.CharacterListAdapter;
import com.boredombabies.charactersheet.db.RealmHelper;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;

import java.util.List;

import io.realm.Realm;

/**
 * Created on 2/7/16.
 */
public class AllySelectDialog extends DialogFragment {

    Realm realm;
    private CharacterListAdapter listAdapter;
    private CharacterListAdapter currentAlliesListAdapter;
    private List<PlayerCharacter> potentialAllies;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        realm = RealmHelper.getRealm(activity);
        potentialAllies = PlayerCharacterHelper.getPotentialAllies(realm);
        listAdapter = new CharacterListAdapter(activity, potentialAllies);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // TODO: prettify header/view
        builder//.setView(inflater.inflate(R.layout.dialog_allies, null))
                .setTitle(R.string.label_pick_ally)
                .setAdapter(listAdapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        saveAlly(potentialAllies.get(which));
                        currentAlliesListAdapter.notifyDataSetChanged();
                        // nonzero in resultcode
                        getTargetFragment().onActivityResult(getTargetRequestCode(), 42, null);
                    }
                });
        return builder.create();
    }

    public void setCurrentAlliesListAdapter(CharacterListAdapter currentAlliesListAdapter) {
        this.currentAlliesListAdapter = currentAlliesListAdapter;
    }

    private void saveAlly(PlayerCharacter newAlly) {
        realm.beginTransaction();
        PlayerCharacterHelper.getActiveCharacter().getAllies().getPlayerCharacterAllies().add(newAlly);
        realm.commitTransaction();
    }
}
