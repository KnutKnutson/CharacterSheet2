package com.boredombabies.charactersheet.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.adapter.CharacterListAdapter;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created on 2/7/16.
 */
public class AllySelectDialogFragment extends DialogFragment {

    AllySelectListener listener;
    Realm realm;
    private CharacterListAdapter listAdapter;
    private List<PlayerCharacter> potentialAllies;

    public interface AllySelectListener {
        public void onSelectNewAlly(PlayerCharacter newAlly);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (AllySelectListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement AllySelectListener");
        }
        realm = Realm.getInstance(getActivity());
        potentialAllies = PlayerCharacterHelper.getPotentialAllies(realm);
        listAdapter = new CharacterListAdapter(activity, potentialAllies);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.label_pick_ally)
                .setAdapter(listAdapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onSelectNewAlly(potentialAllies.get(which));
                    }
                });
        return builder.create();
    }
}
