package com.boredombabies.charactersheet.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.adapter.FragmentSmartPagerAdapter;
import com.boredombabies.charactersheet.adapter.SmartFragmentStatePagerAdapter;
import com.boredombabies.charactersheet.helper.Constants;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;

/**
 * Created by mark.knutson on 4/11/15.
 */
public class CharacterSheetViewPagerFragment extends Fragment {

    private SmartFragmentStatePagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private int viewPagerPreferencesNumber;

    //private OnPagerAdapterFragmentListener mListener;

    public static CharacterSheetViewPagerFragment newInstance(int viewPagerPreferencesNumber) {
        CharacterSheetViewPagerFragment fragment = new CharacterSheetViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.VIEW_PAGER_PREF_NUMBER, viewPagerPreferencesNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CharacterSheetViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            viewPagerPreferencesNumber = getArguments().getInt(Constants.VIEW_PAGER_PREF_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.activity_character_sheet_view_pager, container, false);
        viewPager = (ViewPager) result.findViewById(R.id.viewPager);
        viewPagerAdapter = new FragmentSmartPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(getOnPageChangeListener());
        viewPager.setCurrentItem(getActiveFragmentPage());

        return(result);
    }

    private void saveActiveFragmentPage(int activeFragment) {
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(viewPagerActivePageKey(), activeFragment);
        editor.commit();
    }

    private int getActiveFragmentPage() {
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        return sharedPref.getInt(viewPagerActivePageKey(), viewPagerPreferencesNumber);
    }

    private String viewPagerActivePageKey() {
        return "View Pager Preference File " +
                PlayerCharacterHelper.getActiveCharacter().getId() +
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
