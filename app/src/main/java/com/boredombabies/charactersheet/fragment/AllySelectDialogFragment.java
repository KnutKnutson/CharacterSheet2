package com.boredombabies.charactersheet.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.boredombabies.charactersheet.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2/7/16.
 */
public class AllySelectDialogFragment extends DialogFragment {

    public interface AllySelectListener {
        public void onSelectNewAlly(DialogFragment dialog);
    }

    AllySelectListener listener;
    String[] dummyAllies = new String[]{"ally1", "ally2", "ally3"};

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (AllySelectListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement AllySelectListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.label_pick_ally)
                .setItems(dummyAllies, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item

                    }
                });
        return builder.create();
    }
}
