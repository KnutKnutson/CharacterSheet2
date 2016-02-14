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

public class CharacterSheetViewPagerFragment extends Fragment {

    private SmartFragmentStatePagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private int viewPagerPreferencesNumber;
    private String preferencesKey;

    public static CharacterSheetViewPagerFragment newInstance(int viewPagerPreferenceNumber) {
        CharacterSheetViewPagerFragment fragment = new CharacterSheetViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.VIEW_PAGER_PREF_NUMBER, viewPagerPreferenceNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CharacterSheetViewPagerFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
//            Log.d("VIEWPAGERFRAGMENT", "SAVEDSTATE");
//            for (String key : savedInstanceState.keySet()) {
//                Object value = savedInstanceState.get(key);
//                Log.d("SAVEDSTATE", String.format("%s %s (%s)", key,
//                        value.toString(), value.getClass().getName()));
//            }
        }
        if (getArguments() != null) {
            viewPagerPreferencesNumber = getArguments().getInt(Constants.VIEW_PAGER_PREF_NUMBER);
            preferencesKey = viewPagerActivePageKey();
        }
        viewPagerAdapter = new FragmentSmartPagerAdapter(getChildFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewPager = inflater.inflate(R.layout.fragment_character_sheet_view_pager, container, false);

        return(viewPager);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(getOnPageChangeListener());
        int pageNumber = getActiveFragmentPage();
        viewPager.setCurrentItem(pageNumber);
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    private void saveActiveFragmentPage(int activeFragment) {
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(preferencesKey, activeFragment);
        editor.apply();
    }

    private int getActiveFragmentPage() {
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        int number = sharedPref.getInt(preferencesKey, viewPagerPreferencesNumber - 1);
        return number;
    }

    private String viewPagerActivePageKey() {
        return  "vPAKey" +
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
