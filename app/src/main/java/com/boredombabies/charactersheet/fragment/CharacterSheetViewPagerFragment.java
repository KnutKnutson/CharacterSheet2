package com.boredombabies.charactersheet.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.adapter.FragmentSmartPagerAdapter;
import com.boredombabies.charactersheet.adapter.SmartFragmentStatePagerAdapter;
import com.boredombabies.charactersheet.helper.Constants;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;

/**
 * Created by mark.knutson on 4/11/15.
 */
public class CharacterSheetViewPagerFragment extends Fragment {

    private SmartFragmentStatePagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private int viewPagerPreferencesNumber;

    PlayerCharacter playerCharacter;

    public static CharacterSheetViewPagerFragment newInstance(int viewPagerPreferencesNumber) {
        CharacterSheetViewPagerFragment fragment = new CharacterSheetViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.VIEW_PAGER_PREF_NUMBER, viewPagerPreferencesNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CharacterSheetViewPagerFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        if (getArguments() != null) {
            viewPagerPreferencesNumber = getArguments().getInt(Constants.VIEW_PAGER_PREF_NUMBER);
            Log.e("preference number:", Integer.toString(viewPagerPreferencesNumber));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_character_sheet_view_pager, container, false);
        viewPager = (ViewPager) result.findViewById(R.id.viewPager);
        viewPagerAdapter = new FragmentSmartPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(getOnPageChangeListener());
        viewPager.setCurrentItem(getActiveFragmentPage());

        return(result);
    }

    private void saveActiveFragmentPage(int activeFragment) {
        // TODO: save active fragment in background thread
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(viewPagerActivePageKey(), activeFragment);
        editor.commit();
    }

    private int getActiveFragmentPage() {
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        return sharedPref.getInt(viewPagerActivePageKey(), viewPagerPreferencesNumber);
    }

    private String viewPagerActivePageKey() {
        return PlayerCharacterHelper.getActiveCharacter().getId() +
                Integer.toString(viewPagerPreferencesNumber);
    }

    private ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) { saveActiveFragmentPage(position); }

            @Override
            public void onPageScrollStateChanged(int state) { }
        };// end new onpagechangelistener
    }
}
