package com.boredombabies.charactersheet.model;

/**
 * Created by mark.knutson on 4/7/15.
 */


public class Attack {
    private String name;
    private String attackBonus;
    // private int attackBonus;
    private String damage;
    // private Die damage;
    private String damageType; // unused this version
    private AttackRange range; // unused this version
    private PlayerCharacter playerCharacter;

    public Attack() { super(); }

    public Attack(PlayerCharacter playerCharacter) {
        super();
        this.playerCharacter = playerCharacter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(String attackBonus) {
        this.attackBonus = attackBonus;
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

    public AttackRange getRange() {
        return range;
    }

    public void setRange(AttackRange range) {
        this.range = range;
    }
}
