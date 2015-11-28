package com.boredombabies.charactersheet.helper;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import com.boredombabies.charactersheet.interfaces.EditTextTextListener;

import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;

/**
 * Created by mark.knutson on 11/27/15.
 */
public class EditTextTextWatcher implements TextWatcher, EditTextTextListener {
    private Context context;
    //private Timer timer = new Timer();
    private final long DELAY = 500; // milliseconds

    public EditTextTextWatcher(Context context) {
        this.context = context;
    }

    // EditTextTextListener
    @Override
    public void afterTextChangedCallback(Editable s) {

    }

    @Override
    public void inTransactionCallback(Editable s) {

    }

    // TextWatcher
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(final Editable s) {
//        timer.cancel();
//        timer = new Timer();
//        timer.schedule(
//                new TimerTask() {
//                    @Override
//                    public void run() {
//                    }
//            },
//            DELAY
//        );
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        inTransactionCallback(s);
        realm.commitTransaction();
        afterTextChangedCallback(s);
    }
}
