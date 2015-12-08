package com.boredombabies.charactersheet.helper;

/**
 * Created by mark.knutson on 11/10/15.
 */
public class Constants {
    public static final String PREFERENCE_FILE_KEY    = "BoredomBabies Character Sheet Preference File Key";
    public static final String VIEW_PAGER_PREF_NUMBER = "View Pager Preferences Number";

    // Ability Scores
    public static final String STR = "Strength";
    public static final String DEX = "Dexterity";
    public static final String CON = "Constitution";
    public static final String INT = "Intelligence";
    public static final String WIS = "Wisdom";
    public static final String CHA = "Charisma";

    // Skills
    public static final String ACROBATICS      = "Acrobatics";
    public static final String ANIMAL_HANDLING = "Animal Handling";
    public static final String ARCANA          = "Arcana";
    public static final String ATHLETICS       = "Athletics";
    public static final String DECEPTION       = "Deception";
    public static final String HISTORY         = "History";
    public static final String INSIGHT         = "Insight";
    public static final String INTIMIDATION    = "Intimidation";
    public static final String INVESTIGATION   = "Investigation";
    public static final String MEDICINE        = "Medicine";
    public static final String NATURE          = "Nature";
    public static final String PERCEPTION      = "Perception";
    public static final String PERFORMANCE     = "Performance";
    public static final String PERSUASION      = "Persuasion";
    public static final String RELIGION        = "Religion";
    public static final String SLEIGHT_OF_HAND = "Sleight of Hand";
    public static final String STEALTH         = "Stealth";
    public static final String SURVIVAL        = "Survival";

    // ABILITY SCORES
    public static enum ABILITY_SCORE {
        ABILITY_SCORE_STRENGTH (STR),
        ABILITY_SCORE_DEXTERITY (DEX),
        ABILITY_SCORE_CONSTITUTION (CON),
        ABILITY_SCORE_INTELLIGENCE (INT),
        ABILITY_SCORE_WISDOM (WIS),
        ABILITY_SCORE_CHARISMA (CHA);

        private final String modifier;

        ABILITY_SCORE(String modifier) {
            this.modifier = modifier;
        }

        public String modifier() { return modifier; }
        public String modifierShort() { return modifier.substring(0, 3); }
    }

    // BASE SKILLS
    public static enum BASE_SKILL {
        SKILL_ACROBATICS      (ACROBATICS,      DEX),
        SKILL_ANIMAL_HANDLING (ANIMAL_HANDLING, WIS),
        SKILL_ARCANA          (ARCANA,          INT),
        SKILL_ATHLETICS       (ATHLETICS,       STR),
        SKILL_DECEPTION       (DECEPTION,       CHA),
        SKILL_HISTORY         (HISTORY,         INT),
        SKILL_INSIGHT         (INSIGHT,         WIS),
        SKILL_INTIMIDATION    (INTIMIDATION,    CHA),
        SKILL_INVESTIGATION   (INVESTIGATION,   INT),
        SKILL_MEDICINE        (MEDICINE,        WIS),
        SKILL_NATURE          (NATURE,          INT),
        SKILL_PERCEPTION      (PERCEPTION,      WIS),
        SKILL_PERFORMANCE     (PERFORMANCE,     CHA),
        SKILL_PERSUASION      (PERSUASION,      CHA),
        SKILL_RELIGION        (RELIGION,        INT),
        SKILL_SLEIGHT_OF_HAND (SLEIGHT_OF_HAND, DEX),
        SKILL_STEALTH         (STEALTH,         DEX),
        SKILL_SURVIVAL        (SURVIVAL,        WIS);

        private final String skillName;
        private final String skillModifier;

        BASE_SKILL(String skillName, String skillModifier) {
            this.skillName = skillName;
            this.skillModifier = skillModifier;
        }

        public String skillName() { return skillName; }
        public String skillModifier() { return skillModifier; }
        public String skillModifierShort() { return skillModifier.substring(0, 3); }
    }
}
