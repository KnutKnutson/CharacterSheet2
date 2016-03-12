package com.boredombabies.charactersheet.model;

import com.boredombabies.charactersheet.model.builder.WeaponBuilder;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created on 3/5/16.
 */
public class Weapon extends RealmObject {
    private String name;
    private int resourceName;
    private String proficiency;
    private String cost = "-";
    private String damage = "-";
    private String damageType = "";
    private String weight = "-";
    private String properties = "-";
    private String range = "";
    private String versatileDamage = "";
    private String notes = "";
    private Boolean equipped = false;
    private Boolean useFinesse = false;
    private Boolean useVersatile = false;

    @Ignore
    private String damageString;
    @Ignore
    private String propertiesString;
    @Ignore
    private Boolean isFinesse;
    @Ignore
    private Boolean isVersatile;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceName() {
        return resourceName;
    }

    public void setResourceName(int resourceName) {
        this.resourceName = resourceName;
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


    public String getDamageString() {
        String ds = getDamage();
        if (!getVersatileDamage().equals("")) { ds += " (" + getVersatileDamage() + ")"; }
        if (!getRange().equals("")) { ds += " (" + getRange() + ")"; }
        ds += " " + getDamageType();
        return ds;
    }

    public void setDamageString(String damageString) {
        this.damageString = damageString;
    }

    public String getPropertiesString() {
        return propertiesString;
    }

    public void setPropertiesString(String propertiesString) {
        this.propertiesString = propertiesString;
    }

    public Boolean getIsFinesse() {
        return getProperties().toLowerCase().contains( WeaponBuilder.FINESSE.toLowerCase() );
    }

    public void setIsFinesse(Boolean isFinesse) {
        this.isFinesse = isFinesse;
    }

    public Boolean getIsVersatile() {
        return getProperties().toLowerCase().contains( WeaponBuilder.VERSATILE.toLowerCase() );
    }

    public void setIsVersatile(Boolean isVersatile) {
        this.isVersatile = isVersatile;
    }
}
