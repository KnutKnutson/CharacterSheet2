package com.boredombabies.charactersheet.model;

import io.realm.RealmObject;

/**
 * Created on 3/5/16.
 */
public class Weapon extends RealmObject {
    private String name;
    private String proficiency;
    private String cost;
    private String damage;
    private String damageType;
    private String weight;
    private String properties;
    private String range;
    private String versatileDamage;
    private String notes;
    private Boolean equipped;
    private Boolean useFinesse;
    private Boolean useVersatile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String type) {
        this.proficiency = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getVersatileDamage() {
        return versatileDamage;
    }

    public void setVersatileDamage(String versatileDamage) {
        this.versatileDamage = versatileDamage;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getEquipped() {
        return equipped;
    }

    public void setEquipped(Boolean equipped) {
        this.equipped = equipped;
    }

    public Boolean getUseFinesse() {
        return useFinesse;
    }

    public void setUseFinesse(Boolean useFinesse) {
        this.useFinesse = useFinesse;
    }

    public Boolean getUseVersatile() {
        return useVersatile;
    }

    public void setUseVersatile(Boolean useVersatile) {
        this.useVersatile = useVersatile;
    }
}
