package com.boredombabies.charactersheet.model;

import io.realm.RealmObject;

/**
 * Created by mark.knutson on 4/7/15.
 */
public class Spell extends RealmObject{
    private String name;
    private String level;
    private boolean prepared;
    private boolean learned;
    private String castingTime;
    private String range;
    private String components;
    private String duration;
    private String description;

    public Spell() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public boolean isPrepared() {
        return prepared;
    }
    public void setPrepared(boolean prepared) {
        this.prepared = prepared;
    }

    public boolean isLearned() {
        return learned;
    }

    public void setLearned(boolean learned) {
        this.learned = learned;
    }

    public String getCastingTime() {
        return castingTime;
    }
    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }
    public String getRange() {
        return range;
    }
    public void setRange(String range) {
        this.range = range;
    }
    public String getComponents() {
        return components;
    }
    public void setComponents(String components) {
        this.components = components;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
