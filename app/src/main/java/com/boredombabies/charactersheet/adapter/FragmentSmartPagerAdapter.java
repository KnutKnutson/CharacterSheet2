package com.boredombabies.charactersheet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.boredombabies.charactersheet.fragment.AlliesFragment;
import com.boredombabies.charactersheet.fragment.CharacterAttributesFragment;
import com.boredombabies.charactersheet.fragment.CharacterCombatStatsFragment;
import com.boredombabies.charactersheet.fragment.CharacterProfileFragment;
import com.boredombabies.charactersheet.fragment.EquipmentFragment;
import com.boredombabies.charactersheet.fragment.FeaturesFragment;
import com.boredombabies.charactersheet.fragment.SpellsFragment;

import java.util.ArrayList;

/**
 * Created by mark.knutson on 4/9/15.
 */
//TODO loop around
public class FragmentSmartPagerAdapter extends SmartFragmentStatePagerAdapter {
    private static int NUM_FRAGMENTS = 7;

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
                /**
                 * profile, backstory, picture?
                 */
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
                return new CharacterCombatStatsFragment();
            case 3:
                /**
                 * Spells
                 */
                return new SpellsFragment();
            case 4:
                /**
                 * Features
                 * personality traits, ideals, bonds, flaws, features & traits
                 */
                return new FeaturesFragment();
            case 5:
                /**
                 * Equipment
                 * equipment, treasure
                 */
                return new EquipmentFragment();
            case 6:
                /**
                 * Allies & Organizations
                 * (links to other characters?)
                 */
                return new AlliesFragment();
            default:
                return null;
        }
    }
}
