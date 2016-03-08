package com.boredombabies.charactersheet.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created on 3/7/16.
 */
public class ItemListFragment extends ListFragment {
    public static final int NEW_ITEM_REQUEST_CODE = 42;

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
}
