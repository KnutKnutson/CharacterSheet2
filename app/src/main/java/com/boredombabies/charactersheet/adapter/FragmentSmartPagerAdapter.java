package com.boredombabies.charactersheet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.boredombabies.charactersheet.fragment.CharacterAttributesFragment;
import com.boredombabies.charactersheet.fragment.CharacterProfileFragment;

/**
 * Created by mark.knutson on 4/9/15.
 */
public class FragmentSmartPagerAdapter extends SmartFragmentStatePagerAdapter {
    private static int NUM_FRAGMENTS = 10;

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
                /**
                 * Attributes include:
                 * ability scores, inspiration, prof. bonus, skills, passive wisdom,
                 * other proficiencies and languages
                 */
                return new CharacterAttributesFragment();

            case 2:
                /**
                 * Combat
                 * AC, initiative, speed, hp max, current hp, hit dice, hit dice total, death saves,
                 * equipped weapons (name, bonus, damage/type), attacks, readied spells
                 */
                return new CharacterProfileFragment();
            case 3:
                /**
                 * Equipment
                 */
                return new CharacterProfileFragment();
            case 4:
                /**
                 * Traits
                 * personality traits, ideals, bonds, flaws, features & traits
                 */
                return new CharacterProfileFragment();
            case 5:
                return new CharacterProfileFragment();
            case 6:
                return new CharacterProfileFragment();
            case 7:
                return new CharacterProfileFragment();
            case 8:
                return new CharacterProfileFragment();
            case 9:
                return new CharacterProfileFragment();
            /*
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
