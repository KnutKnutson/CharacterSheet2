package com.boredombabies.charactersheet.interfaces;

import android.text.Editable;

/**
 * Created by mark.knutson on 11/27/15.
 */
public interface EditTextTextListener {
    void afterTextChangedCallback(Editable s);
    void inTransactionCallback(Editable s);
}
