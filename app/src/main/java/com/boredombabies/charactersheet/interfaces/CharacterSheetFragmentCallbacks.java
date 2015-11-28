package com.boredombabies.charactersheet.interfaces;

import android.net.Uri;

/**
 * Created by mark.knutson on 4/18/15.
 */
public interface CharacterSheetFragmentCallbacks {
    public void refreshFragments(int callingFragmentId);
    public void setHeaderText(String s);
    public void setHeaderImage(int drawable);
}
