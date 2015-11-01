package com.boredombabies.charactersheet.model;

/**
 * Created by mark.knutson on 4/7/15.
 */
public class Item {
    private String name;
    private String description;

    public Item() {

    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
