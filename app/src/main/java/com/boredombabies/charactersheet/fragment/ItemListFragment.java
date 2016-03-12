package com.boredombabies.charactersheet.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created on 3/7/16.
 */
public abstract class ItemListFragment extends ListFragment {
    public static final int NEW_ITEM_REQUEST_CODE = 42;

    // Tracks current menu item
    private int itemToRemoveFromList;
    // Tracks current contextual action mode
    private ActionMode currentActionMode;

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        ListView listView = getListView();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentActionMode != null) {
                    return false;
                }
                itemToRemoveFromList = position;
                currentActionMode = getActivity().startActionMode(modeCallBack);
                view.setSelected(true);
                return true;
            }
        });

        setListViewHeightBasedOnItems(listView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST_CODE && resultCode != Activity.RESULT_CANCELED) {
            setListViewHeightBasedOnItems(getListView());
        }
    }

    public void setListViewHeightBasedOnItems(ListView listView) {
        int numberOfItems = getListAdapter().getCount();

        // Get total height of all items.
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = getListAdapter().getView(itemPos, null, listView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight() + 30; // little extra love.
        }

        // Get total height of all item dividers.
        int totalDividersHeight = listView.getDividerHeight() *
                (numberOfItems - 1);

        // Set list height.
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    private ActionMode.Callback modeCallBack = getActionModeCallback();

    public int getItemToRemove() {
        return itemToRemoveFromList;
    }

    public ActionMode getCurrentActionMode() {
        return currentActionMode;
    }

    public void setCurrentActionMode(ActionMode currentActionMode) {
        this.currentActionMode = currentActionMode;
    }

    public abstract ActionMode.Callback getActionModeCallback();
}
