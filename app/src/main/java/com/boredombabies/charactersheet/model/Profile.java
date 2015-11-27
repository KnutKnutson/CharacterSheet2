package com.boredombabies.charactersheet.model;

import io.realm.RealmObject;

/**
 * Created by mark.knutson on 3/22/15.
 */
public class Profile extends RealmObject {
    private int level = 1;
    private String playerName = "";
    private String background = "";
    private String alignment = "";
    private int experiencePoints = 0;
    private String age = "";
    private String height = "";
    private String weight = "";
    private String eyes = "";
    private String skin = "";
    private String hair = "";

    public Profile() { }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getBackground() {
        return background;
    }
    public void setBackground(String background) {
        this.background = background;
    }
    public String getAlignment() {
        return alignment;
    }
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
    public int getExperiencePoints() {
        return experiencePoints;
    }
    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getEyes() {
        return eyes;
    }
    public void setEyes(String eyes) {
        this.eyes = eyes;
    }
    public String getSkin() {
        return skin;
    }
    public void setSkin(String skin) {
        this.skin = skin;
    }
    public String getHair() {
        return hair;
    }
    public void setHair(String hair) {
        this.hair = hair;
    }
}
