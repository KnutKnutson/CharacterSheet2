package com.boredombabies.charactersheet.model;

import io.realm.RealmObject;

/**
 * Created by mark.knutson on 4/7/15.
 */
public class Equipment extends RealmObject{
    private String equipment;
    private String treasure;
    private String copper;
    private String silver;
    private String electrum;
    private String gold;
    private String platinum;


    public Equipment() {}

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

    public String getCopper() {
        return copper;
    }

    public void setCopper(String copper) {
        this.copper = copper;
    }

    public String getSilver() {
        return silver;
    }

    public void setSilver(String silver) {
        this.silver = silver;
    }

    public String getElectrum() {
        return electrum;
    }

    public void setElectrum(String electrum) {
        this.electrum = electrum;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getPlatinum() {
        return platinum;
    }

    public void setPlatinum(String platinum) {
        this.platinum = platinum;
    }
}
