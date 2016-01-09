package com.boredombabies.charactersheet.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by mark.knutson on 4/7/15.
 */
public class Equipment extends RealmObject{
    private RealmList<Coin> coins;
    private String equipment;
    private String treasure;

    public Equipment() {}

    public RealmList<Coin> getCoins() {
        return coins;
    }

    public void setCoins(RealmList<Coin> coins) {
        this.coins = coins;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getTreasure() {
        return treasure;
    }

    public void setTreasure(String treasure) {
        this.treasure = treasure;
    }
}
