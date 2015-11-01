package com.boredombabies.charactersheet.model;

/**
 * Created by mark.knutson on 4/7/15.
 */
public class Weapon extends Item {
    private Attack attack;

    public Weapon(String name) {
        attack = new Attack();
        attack.setName(name);
        setName(name);
    }
}
