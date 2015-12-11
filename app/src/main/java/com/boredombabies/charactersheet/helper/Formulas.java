package com.boredombabies.charactersheet.helper;

import com.boredombabies.charactersheet.R;

/**
 * Created by mark.knutson on 12/7/15.
 */
public class Formulas {
    static public int getClassImage(String className) {
        switch (className.toLowerCase()) {
            case "barbarian":
                return R.drawable.barbarian_small;
            case "bard":
                return R.drawable.bard_small;
            case "cleric":
                return R.drawable.cleric_small;
            case "druid":
                return R.drawable.druid_small;
            case "fighter":
                return R.drawable.fighter_small;
            case "monk":
                return R.drawable.monk_small;
            case "paladin":
                return R.drawable.paladin_small;
            case "ranger":
                return R.drawable.ranger_small;
            case "rogue":
                return R.drawable.rogue_small;
            case "sorcerer":
                return R.drawable.sorcerer_small;
            case "warlock":
                return R.drawable.warlock_small;
            case "wizard":
                return R.drawable.wizard_small;
            default:
                return R.drawable.barbarian_small;
        }
    }
}
