package com.boredombabies.charactersheet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.boredombabies.charactersheet.fragment.CharacterProfileFragment;

/**
 * Created by mark.knutson on 4/9/15.
 */
public class FragmentSmartPagerAdapter extends SmartFragmentStatePagerAdapter {
    private static int NUM_FRAGMENTS = 5;

    public FragmentSmartPagerAdapter(FragmentManager fragmentManager) { super(fragmentManager); }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_FRAGMENTS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    private Fragment getFragment(int position) {
        switch (position) {
            case 0:
                return new CharacterProfileFragment();
            case 1:
                return new CharacterProfileFragment();
            case 2:
                return new CharacterProfileFragment();
            case 3:
                return new CharacterProfileFragment();
            case 4:
                return new CharacterProfileFragment();
            /*
            case 5:
                return BackgroundDetailsFragment.newInstance();
            case 6:
                return FeaturesAndTraitsFragment.newInstance();
            case 7:
                return EquipmentFragment.newInstance();
            case 8:
                return ProficienciesAndLanguagesFragment.newInstance();
            case 9:
                return TreasureFragment.newInstance();
            case 10:
                return SpellsFragment.newInstance();
            case 11:
                return AlliesAndOrganizationsFragment.newInstance();
            case 12:
                // Make this? get rid of other notesy type fragments?
                return NotesFragment.newInstance();
                */
            default:
                return null;
        }
    }
}
