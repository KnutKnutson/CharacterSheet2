package com.boredombabies.charactersheet.model;


import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by mark.knutson on 5/5/15.
 */
public class AbilityScore extends RealmObject {

    private String name;
    private int abilityScore = 10;

    @Ignore
    private int abilityModifier;

    public AbilityScore() { }

    public AbilityScore(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAbilityScore() {
        return abilityScore;
    }
    public void setAbilityScore(int abilityScore) {
        this.abilityScore = abilityScore;
    }
    public int getAbilityModifier() {
        int base = getAbilityScore() - 10;
        return (base < 0 ? (base - 1) / 2 : base / 2);
    }
}
