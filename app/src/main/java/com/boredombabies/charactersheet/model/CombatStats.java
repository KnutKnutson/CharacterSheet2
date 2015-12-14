package com.boredombabies.charactersheet.model;


import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by mark.knutson on 6/11/15.
 */

public class CombatStats extends RealmObject {

    private String armorClass;
    private String initiative;
    private String speed;
    private String hitPointMax;
    private String hitPointCurrent;
    private String hitPointTemp;
    private String hitDiceTotal;
    private String hitDice;
//    private List<Boolean> deathSaveSuccesses;
//    private List<Boolean> deathSaveFailures;
    private boolean deathSaveSuccess0;
    private boolean deathSaveSuccess1;
    private boolean deathSaveSuccess2;
    private boolean deathSaveFailure0;
    private boolean deathSaveFailure1;
    private boolean deathSaveFailure2;

    private RealmList<Attack> attacks = new RealmList<>();

    public CombatStats() {
        for (int i = 0; i < 3; i++) {
            attacks.add(new Attack());
        }
    }

    public String getArmorClass() {
        return armorClass;
    }
    public void setArmorClass(String armorClass) {
        this.armorClass = armorClass;
    }
    public String getInitiative() {
        return initiative;
    }
    public void setInitiative(String initiative) {
        this.initiative = initiative;
    }
    public String getSpeed() {
        return speed;
    }
    public void setSpeed(String speed) {
        this.speed = speed;
    }
    public String getHitPointMax() {
        return hitPointMax;
    }
    public void setHitPointMax(String hitPointMax) {
        this.hitPointMax = hitPointMax;
    }
    public String getHitPointCurrent() {
        return hitPointCurrent;
    }
    public void setHitPointCurrent(String hitPointCurrent) {
        this.hitPointCurrent = hitPointCurrent;
    }
    public String getHitPointTemp() {
        return hitPointTemp;
    }
    public void setHitPointTemp(String hitPointTemp) {
        this.hitPointTemp = hitPointTemp;
    }
    public String getHitDiceTotal() {
        return hitDiceTotal;
    }
    public void setHitDiceTotal(String hitDiceTotal) {
        this.hitDiceTotal = hitDiceTotal;
    }
    public String getHitDice() {
        return hitDice;
    }
    public void setHitDice(String hitDice) {
        this.hitDice = hitDice;
    }
    public boolean isDeathSaveSuccess0() {
        return deathSaveSuccess0;
    }
    public void setDeathSaveSuccess0(boolean deathSaveSuccess0) {
        this.deathSaveSuccess0 = deathSaveSuccess0;
    }
    public boolean isDeathSaveSuccess1() {
        return deathSaveSuccess1;
    }
    public void setDeathSaveSuccess1(boolean deathSaveSuccess1) {
        this.deathSaveSuccess1 = deathSaveSuccess1;
    }
    public boolean isDeathSaveSuccess2() {
        return deathSaveSuccess2;
    }
    public void setDeathSaveSuccess2(boolean deathSaveSuccess2) {
        this.deathSaveSuccess2 = deathSaveSuccess2;
    }
    public boolean isDeathSaveFailure0() {
        return deathSaveFailure0;
    }
    public void setDeathSaveFailure0(boolean deathSaveFailure0) {
        this.deathSaveFailure0 = deathSaveFailure0;
    }
    public boolean isDeathSaveFailure1() {
        return deathSaveFailure1;
    }
    public void setDeathSaveFailure1(boolean deathSaveFailure1) {
        this.deathSaveFailure1 = deathSaveFailure1;
    }
    public boolean isDeathSaveFailure2() {
        return deathSaveFailure2;
    }
    public void setDeathSaveFailure2(boolean deathSaveFailure2) {
        this.deathSaveFailure2 = deathSaveFailure2;
    }

    public RealmList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(RealmList<Attack> attacks) {
        this.attacks = attacks;
    }
    //    public List<Boolean> getDeathSaveSuccesses() {
//        return deathSaveSuccesses;
//    }
//    public void setDeathSaveSuccesses(List<Boolean> deathSaveSuccesses) {
//        this.deathSaveSuccesses = deathSaveSuccesses;
//    }
//    public List<Boolean> getDeathSaveFailures() {
//        return deathSaveFailures;
//    }
//    public void setDeathSaveFailures(List<Boolean> deathSaveFailures) {
//        this.deathSaveFailures = deathSaveFailures;
//    }
}
