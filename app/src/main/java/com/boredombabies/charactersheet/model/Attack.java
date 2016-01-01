package com.boredombabies.charactersheet.model;

import io.realm.RealmObject;

/**
 * Created by mark.knutson on 4/7/15.
 */


public class Attack extends RealmObject {
    private String name;
    private String attackBonus;
    private String damageAndType;

    public Attack() { }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAttackBonus() {
        return attackBonus;
    }
    public void setAttackBonus(String attackBonus) {
        this.attackBonus = attackBonus;
    }
    public String getDamageAndType() {
        return damageAndType;
    }
    public void setDamageAndType(String damageAndType) {
        this.damageAndType = damageAndType;
    }
}
