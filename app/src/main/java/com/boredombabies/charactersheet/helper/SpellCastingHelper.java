package com.boredombabies.charactersheet.helper;


/**
 * Created by mark.knutson on 1/2/16.
 * Business Logic for SpellCasting
 */
public class SpellCastingHelper {
    public static int getSpellsPerSlotLevel(int slotLevel) {
        switch (slotLevel) {
            case 0:
                return 8;
            case 1: case 2: case 3: case 4:
                return 13;
            case 5: case 6: case 7:
                return 9;
            case 8: case 9:
                return 7;
            default:
                return 5;
        }
    }
}
