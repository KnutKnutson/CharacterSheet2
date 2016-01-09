package com.boredombabies.charactersheet.model;

import io.realm.RealmObject;

/**
 * Created by mark.knutson on 4/7/15.
 */
public class Coin extends RealmObject{
    private String coinName;
    private String amount;

    public Coin() {
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
