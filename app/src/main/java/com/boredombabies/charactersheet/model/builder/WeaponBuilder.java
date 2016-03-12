package com.boredombabies.charactersheet.model.builder;

import android.text.TextUtils;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.model.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 3/5/16.
 */
public class WeaponBuilder {
    // SIMPLE MELEE
    public static final int CLUBR = R.string.CLUB;
    public static final String CLUB = "Club";
    public static final String DAGGER = "Dagger";
    public static final String GREATCLUB = "Greatclub";
    public static final String HANDAXE = "Handaxe";
    public static final String JAVELIN = "Javelin";
    public static final String LIGHT_HAMMER = "Light Hammer";
    public static final String MACE = "Mace";
    public static final String QUARTERSTAFF = "Quarterstaff";
    public static final String SICKLE = "Sickle";
    public static final String SPEAR = "Spear";
    public static final String UNARMED_STRIKE = "Unarmed Strike";
    // SIMPLE RANGED
    public static final String LIGHT_CROSSBOW = "Light Crossbow";
    public static final String DART = "Dart";
    public static final String SHORTBOW = "Shortbow";
    public static final String SLING = "Sling";
    // MARTIAL MELEE
    public static final String BATTLEAXE = "Battleaxe";
    public static final String FLAIL = "Flail";
    public static final String GLAIVE = "Glaive";
    public static final String GREATAXE = "Greataxe";
    public static final String GREATSWORD = "Greatsword";
    public static final String HALBERD = "Halberd";
    public static final String LANCE = "Lance";
    public static final String LONGSWORD = "Longsword";
    public static final String MAUL = "Maul";
    public static final String MORNINGSTAR = "Morningstar";
    public static final String PIKE = "Pike";
    public static final String RAPIER = "Rapier";
    public static final String SCIMITAR = "Scimitar";
    public static final String SHORTSWORD = "Shortsword";
    public static final String TRIDENT = "Trident";
    public static final String WAR_PICK = "War Pick";
    public static final String WARHAMMER = "Warhammer";
    public static final String WHIP = "Whip";
    // MARTIAL RANGED
    public static final String BLOWGUN = "Blowgun";
    public static final String HAND_CROSSBOW = "Hand Crossbow";
    public static final String HEAVY_CROSSBOW = "Heavy Crossbow";
    public static final String LONGBOW = "Longbow";
    public static final String NET = "Net";

    public static final String[] WEAPONS = {CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER,
            MACE, QUARTERSTAFF, SICKLE, SPEAR, UNARMED_STRIKE, LIGHT_CROSSBOW, DART, SHORTBOW,
            SLING, BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE, LONGSWORD, MAUL,
            MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP,
            BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET};

    // PROPERTIES
    public static final String AMMUNITION = "Ammunition";
    public static final String FINESSE = "Finesse";
    public static final String HEAVY = "Heavy";
    public static final String LIGHT = "Light";
    public static final String LOADING = "Loading";
    public static final String RANGE = "Range";
    public static final String REACH = "Reach";
    public static final String SPECIAL = "Special";
    public static final String THROWN = "Thrown";
    public static final String TWO_HANDED = "Two Handed";
    public static final String VERSATILE = "Versatile";
    // DAMAGE TYPES
    public static final String BLUDGEONING = "Bludgeoning";
    public static final String PIERCING = "Piercing";
    public static final String SLASHING = "Slashing";
    //
    public static final String SIMPLE = "Simple";
    public static final String MARTIAL = "Martial";

    public static final String MELEE = "Melee";
    public static final String RANGED = "Ranged";
    // COSTS TODO: MOVE TO CONSTANT ELSEWHERE
    public static final String CP = "cp";
    public static final String SP = "sp";
    public static final String GP = "gp";

    public static List<Weapon> buildAll() {
        List<Weapon> weapons = new ArrayList<>();
        for (String weaponName : WEAPONS) {
            weapons.add(build(weaponName));
        }
        return weapons;
    }

    public static Weapon build(String weapon) {
        switch (weapon) {
            case CLUB:
                return club();
            case DAGGER:
                return dagger();
            case GREATCLUB:
                return greatclub();
            case HANDAXE:
                return handaxe();
            case JAVELIN:
                return javelin();
            case LIGHT_HAMMER:
                return lightHammer();
            case MACE:
                return mace();
            case QUARTERSTAFF:
                return quarterstaff();
            case SICKLE:
                return sickle();
            case SPEAR:
                return spear();
            case UNARMED_STRIKE:
                return unarmedStrike();
            case LIGHT_CROSSBOW:
                return lightCrossbow();
            case DART:
                return dart();
            case SHORTBOW:
                return shortBow();
            case SLING:
                return sling();
            case BATTLEAXE:
                return battleaxe();
            case FLAIL:
                return flail();
            case GLAIVE:
                return glaive();
            case GREATAXE:
                return greataxe();
            case GREATSWORD:
                return greatsword();
            case HALBERD:
                return halberd();
            case LANCE:
                return lance();
            case LONGSWORD:
                return longsword();
            case MAUL:
                return maul();
            case MORNINGSTAR:
                return morningstar();
            case PIKE:
                return pike();
            case RAPIER:
                return rapier();
            case SCIMITAR:
                return scimitar();
            case SHORTSWORD:
                return shortsword();
            case TRIDENT:
                return trident();
            case WAR_PICK:
                return warPick();
            case WARHAMMER:
                return warhammer();
            case WHIP:
                return whip();
            case BLOWGUN:
                return blowgun();
            case HAND_CROSSBOW:
                return handCrossbow();
            case HEAVY_CROSSBOW:
                return heavyCrossbow();
            case LONGBOW:
                return longbow();
            case NET:
                return net();
            default:
                return null;
        }
    }

    public static Weapon club() {
        Weapon weapon = new Weapon();
        weapon.setName(CLUB);
        weapon.setResourceName(CLUBR);
        weapon.setProficiency(proficiency(SIMPLE, MELEE));
        weapon.setCost(cost(1, SP));
        weapon.setDamage(die(1, 4));
        weapon.setDamageType(BLUDGEONING);
        weapon.setWeight(weight(2));
        weapon.setProperties(properties(LIGHT));
        return weapon;
    }

    public static Weapon dagger() {
        Weapon weapon = new Weapon();
        weapon.setName(DAGGER);
        weapon.setProficiency(proficiency(SIMPLE, MELEE));
        weapon.setCost(cost(2, GP));
        weapon.setDamage(die(1, 4));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(1));
        weapon.setProperties(properties(LIGHT, FINESSE, THROWN));
        weapon.setRange(range(20, 60));
        return weapon;
    }

    public static Weapon greatclub() {
        Weapon weapon = new Weapon();
        weapon.setName(GREATCLUB);
        weapon.setProficiency(proficiency(SIMPLE, MELEE));
        weapon.setCost(cost(2, SP));
        weapon.setDamage(die(1, 8));
        weapon.setDamageType(BLUDGEONING);
        weapon.setWeight(weight(10));
        weapon.setProperties(properties(TWO_HANDED));
        return weapon;
    }

    public static Weapon handaxe() {
        Weapon weapon = new Weapon();
        weapon.setName(HANDAXE);
        weapon.setProficiency(proficiency(SIMPLE, MELEE));
        weapon.setCost(cost(5, GP));
        weapon.setDamage(die(1, 6));
        weapon.setDamageType(SLASHING);
        weapon.setWeight(weight(2));
        weapon.setProperties(properties(LIGHT, THROWN));
        weapon.setRange(range(20, 60));
        return weapon;
    }

    public static Weapon javelin() {
        Weapon weapon = new Weapon();
        weapon.setName(JAVELIN);
        weapon.setProficiency(proficiency(SIMPLE, MELEE));
        weapon.setCost(cost(5, SP));
        weapon.setDamage(die(1, 6));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(2));
        weapon.setProperties(properties(THROWN));
        weapon.setRange(range(30, 120));
        return weapon;
    }

    public static Weapon lightHammer() {
        Weapon weapon = new Weapon();
        weapon.setName(LIGHT_HAMMER);
        weapon.setProficiency(proficiency(SIMPLE, MELEE));
        weapon.setCost(cost(2, GP));
        weapon.setDamage(die(1, 4));
        weapon.setDamageType(BLUDGEONING);
        weapon.setWeight(weight(2));
        weapon.setProperties(properties(LIGHT, THROWN));
        weapon.setRange(range(20, 60));
        return weapon;
    }

    public static Weapon mace() {
        Weapon weapon = new Weapon();
        weapon.setName(MACE);
        weapon.setProficiency(proficiency(SIMPLE, MELEE));
        weapon.setCost(cost(5, GP));
        weapon.setDamage(die(1, 6));
        weapon.setDamageType(BLUDGEONING);
        weapon.setWeight(weight(4));
        return weapon;
    }

    public static Weapon quarterstaff() {
        Weapon weapon = new Weapon();
        weapon.setName(QUARTERSTAFF);
        weapon.setProficiency(proficiency(SIMPLE, MELEE));
        weapon.setCost(cost(2, SP));
        weapon.setDamage(die(1, 6));
        weapon.setDamageType(BLUDGEONING);
        weapon.setWeight(weight(4));
        weapon.setProperties(properties(VERSATILE));
        weapon.setVersatileDamage(die(1, 8));
        return weapon;
    }

    public static Weapon sickle() {
        Weapon weapon = new Weapon();
        weapon.setName(SICKLE);
        weapon.setProficiency(proficiency(SIMPLE, MELEE));
        weapon.setCost(cost(1, GP));
        weapon.setDamage(die(1, 4));
        weapon.setDamageType(SLASHING);
        weapon.setWeight(weight(2));
        weapon.setProperties(properties(LIGHT));
        return weapon;
    }

    public static Weapon spear() {
        Weapon weapon = new Weapon();
        weapon.setName(SPEAR);
        weapon.setProficiency(proficiency(SIMPLE, MELEE));
        weapon.setCost(cost(1, GP));
        weapon.setDamage(die(1, 6));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(3));
        weapon.setProperties(properties(THROWN, VERSATILE));
        weapon.setRange(range(20, 60));
        weapon.setVersatileDamage(die(1, 8));
        return weapon;
    }

    public static Weapon unarmedStrike() {
        Weapon weapon = new Weapon();
        weapon.setName(UNARMED_STRIKE);
        weapon.setProficiency(proficiency(SIMPLE, MELEE));
        weapon.setDamage("1");
        weapon.setDamageType(BLUDGEONING);
        return weapon;
    }

    public static Weapon lightCrossbow() {
        Weapon weapon = new Weapon();
        weapon.setName(LIGHT_CROSSBOW);
        weapon.setProficiency(proficiency(SIMPLE, RANGED));
        weapon.setCost(cost(25, GP));
        weapon.setDamage(die(1, 8));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(5));
        weapon.setProperties(properties(AMMUNITION, LOADING, TWO_HANDED));
        weapon.setRange(range(80, 320));
        return weapon;
    }

    public static Weapon dart() {
        Weapon weapon = new Weapon();
        weapon.setName(DART);
        weapon.setProficiency(proficiency(SIMPLE, RANGED));
        weapon.setCost(cost(5, CP));
        weapon.setDamage(die(1, 4));
        weapon.setDamageType(PIERCING);
        weapon.setWeight("1/4 lb.");
        weapon.setProperties(properties(FINESSE, THROWN));
        weapon.setRange(range(20, 60));
        return weapon;
    }

    public static Weapon shortBow() {
        Weapon weapon = new Weapon();
        weapon.setName(SHORTBOW);
        weapon.setProficiency(proficiency(SIMPLE, RANGED));
        weapon.setCost(cost(25, GP));
        weapon.setDamage(die(1, 6));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(2));
        weapon.setProperties(properties(AMMUNITION, TWO_HANDED));
        weapon.setRange(range(80, 320));
        return weapon;
    }

    public static Weapon sling() {
        Weapon weapon = new Weapon();
        weapon.setName(SLING);
        weapon.setProficiency(proficiency(SIMPLE, RANGED));
        weapon.setCost(cost(1, SP));
        weapon.setDamage(die(1, 4));
        weapon.setDamageType(BLUDGEONING);
        weapon.setProperties(properties(AMMUNITION));
        weapon.setRange(range(30, 120));
        return weapon;
    }

    public static Weapon battleaxe() {
        Weapon weapon = new Weapon();
        weapon.setName(BATTLEAXE);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(10, GP));
        weapon.setDamage(die(1, 8));
        weapon.setDamageType(SLASHING);
        weapon.setWeight(weight(4));
        weapon.setProperties(properties(VERSATILE));
        weapon.setVersatileDamage(die(1, 10));
        return weapon;
    }

    public static Weapon flail() {
        Weapon weapon = new Weapon();
        weapon.setName(FLAIL);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(10, GP));
        weapon.setDamage(die(1, 8));
        weapon.setDamageType(BLUDGEONING);
        weapon.setWeight(weight(2));
        return weapon;
    }

    public static Weapon glaive() {
        Weapon weapon = new Weapon();
        weapon.setName(GLAIVE);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(20, GP));
        weapon.setDamage(die(1, 10));
        weapon.setDamageType(SLASHING);
        weapon.setWeight(weight(6));
        weapon.setProperties(properties(HEAVY, REACH, TWO_HANDED));
        return weapon;
    }

    public static Weapon greataxe() {
        Weapon weapon = new Weapon();
        weapon.setName(GREATAXE);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(30, GP));
        weapon.setDamage(die(1, 12));
        weapon.setDamageType(SLASHING);
        weapon.setWeight(weight(7));
        weapon.setProperties(properties(HEAVY, TWO_HANDED));
        return weapon;
    }

    public static Weapon greatsword() {
        Weapon weapon = new Weapon();
        weapon.setName(GREATSWORD);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(50, GP));
        weapon.setDamage(die(2, 6));
        weapon.setDamageType(SLASHING);
        weapon.setWeight(weight(6));
        weapon.setProperties(properties(HEAVY, TWO_HANDED));
        return weapon;
    }

    public static Weapon halberd() {
        Weapon weapon = new Weapon();
        weapon.setName(HALBERD);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(20, GP));
        weapon.setDamage(die(1, 10));
        weapon.setDamageType(SLASHING);
        weapon.setWeight(weight(6));
        weapon.setProperties(properties(HEAVY, REACH, TWO_HANDED));
        return weapon;
    }

    public static Weapon lance() {
        Weapon weapon = new Weapon();
        weapon.setName(LANCE);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(10, GP));
        weapon.setDamage(die(1, 12));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(6));
        weapon.setProperties(properties(REACH, SPECIAL));
        return weapon;
    }

    public static Weapon longsword() {
        Weapon weapon = new Weapon();
        weapon.setName(LONGSWORD);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(15, GP));
        weapon.setDamage(die(1, 8));
        weapon.setDamageType(SLASHING);
        weapon.setWeight(weight(3));
        weapon.setProperties(properties(VERSATILE));
        weapon.setVersatileDamage(die(1, 10));
        return weapon;
    }

    public static Weapon maul() {
        Weapon weapon = new Weapon();
        weapon.setName(MAUL);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(10, GP));
        weapon.setDamage(die(2, 6));
        weapon.setDamageType(BLUDGEONING);
        weapon.setWeight(weight(10));
        weapon.setProperties(properties(HEAVY, TWO_HANDED));
        return weapon;
    }

    public static Weapon morningstar() {
        Weapon weapon = new Weapon();
        weapon.setName(MORNINGSTAR);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(15, GP));
        weapon.setDamage(die(1, 8));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(4));
        return weapon;
    }

    public static Weapon pike() {
        Weapon weapon = new Weapon();
        weapon.setName(PIKE);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(5, GP));
        weapon.setDamage(die(1, 10));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(18));
        weapon.setProperties(properties(HEAVY, REACH, TWO_HANDED));
        return weapon;
    }

    public static Weapon rapier() {
        Weapon weapon = new Weapon();
        weapon.setName(RAPIER);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(25, GP));
        weapon.setDamage(die(1, 8));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(2));
        weapon.setProperties(properties(FINESSE));
        return weapon;
    }

    public static Weapon scimitar() {
        Weapon weapon = new Weapon();
        weapon.setName(SCIMITAR);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(25, GP));
        weapon.setDamage(die(1, 6));
        weapon.setDamageType(SLASHING);
        weapon.setWeight(weight(3));
        weapon.setProperties(properties(FINESSE, LIGHT));
        return weapon;
    }

    public static Weapon shortsword() {
        Weapon weapon = new Weapon();
        weapon.setName(SHORTSWORD);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(10, GP));
        weapon.setDamage(die(1, 6));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(2));
        weapon.setProperties(properties(FINESSE, LIGHT));
        return weapon;
    }

    public static Weapon trident() {
        Weapon weapon = new Weapon();
        weapon.setName(TRIDENT);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(5, GP));
        weapon.setDamage(die(1, 6));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(4));
        weapon.setProperties(properties(THROWN, VERSATILE));
        weapon.setRange(range(20, 60));
        weapon.setVersatileDamage(die(1, 8));
        return weapon;
    }

    public static Weapon warPick() {
        Weapon weapon = new Weapon();
        weapon.setName(WAR_PICK);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(5, GP));
        weapon.setDamage(die(1, 8));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(2));
        return weapon;
    }

    public static Weapon warhammer() {
        Weapon weapon = new Weapon();
        weapon.setName(WARHAMMER);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(15, GP));
        weapon.setDamage(die(1, 8));
        weapon.setDamageType(BLUDGEONING);
        weapon.setWeight(weight(2));
        weapon.setProperties(properties(VERSATILE));
        weapon.setVersatileDamage(die(1, 10));
        return weapon;
    }

    public static Weapon whip() {
        Weapon weapon = new Weapon();
        weapon.setName(WHIP);
        weapon.setProficiency(proficiency(MARTIAL, MELEE));
        weapon.setCost(cost(2, GP));
        weapon.setDamage(die(1, 4));
        weapon.setDamageType(SLASHING);
        weapon.setWeight(weight(3));
        weapon.setProperties(properties(FINESSE, REACH));
        return weapon;
    }

    public static Weapon blowgun() {
        Weapon weapon = new Weapon();
        weapon.setName(BLOWGUN);
        weapon.setProficiency(proficiency(MARTIAL, RANGED));
        weapon.setCost(cost(10, GP));
        weapon.setDamage("1");
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(1));
        weapon.setProperties(properties(AMMUNITION, LOADING));
        weapon.setRange(range(25, 100));
        return weapon;
    }

    public static Weapon handCrossbow() {
        Weapon weapon = new Weapon();
        weapon.setName(HAND_CROSSBOW);
        weapon.setProficiency(proficiency(MARTIAL, RANGED));
        weapon.setCost(cost(75, GP));
        weapon.setDamage(die(1, 6));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(3));
        weapon.setProperties(properties(AMMUNITION, LIGHT, LOADING));
        weapon.setRange(range(30, 120));
        return weapon;
    }

    public static Weapon heavyCrossbow() {
        Weapon weapon = new Weapon();
        weapon.setName(HEAVY_CROSSBOW);
        weapon.setProficiency(proficiency(MARTIAL, RANGED));
        weapon.setCost(cost(50, GP));
        weapon.setDamage(die(1, 10));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(18));
        weapon.setProperties(properties(AMMUNITION, HEAVY, LOADING, TWO_HANDED));
        weapon.setRange(range(100, 400));
        return weapon;
    }

    public static Weapon longbow() {
        Weapon weapon = new Weapon();
        weapon.setName(LONGBOW);
        weapon.setProficiency(proficiency(MARTIAL, RANGED));
        weapon.setCost(cost(50, GP));
        weapon.setDamage(die(1, 8));
        weapon.setDamageType(PIERCING);
        weapon.setWeight(weight(2));
        weapon.setProperties(properties(AMMUNITION, HEAVY, TWO_HANDED));
        weapon.setRange(range(150, 600));
        return weapon;
    }

    public static Weapon net() {
        Weapon weapon = new Weapon();
        weapon.setName(NET);
        weapon.setProficiency(proficiency(MARTIAL, RANGED));
        weapon.setCost(cost(1, GP));
        weapon.setWeight(weight(3));
        weapon.setProperties(properties(SPECIAL, THROWN));
        weapon.setRange(range(5, 15));
        return weapon;
    }

    private static String die(int quantity, int sides) {
        return Integer.toString(quantity) + "d" + Integer.toString(sides);
    }

    private static String weight(int quantity) {
        return Integer.toString(quantity) + " lb.";
    }

    private static String range(int min, int max) {
        return RANGE + " " + Integer.toString(min) + "/" + Integer.toString(max);
    }

    private static String cost(int quantity, String denomination) {
        return Integer.toString(quantity) + " " + denomination;
    }

    private static String proficiency(String prof, String type) {
        return prof + " " + type;
    }

    private static String properties(String... properties) {
        return TextUtils.join(", ", properties);
    }
}
