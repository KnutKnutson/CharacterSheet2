package com.boredombabies.charactersheet.model;

/**
 * Created by mark.knutson on 4/7/15.
 */
public class Die {

    private int quantity;
    private int numSides;

    public Die(int quantity, int numSides) {
        setQuantity(quantity);
        setNumSides(numSides);
    }

    public String prettyPrint() {
        return getQuantity() + "d" + getNumSides();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNumSides() {
        return numSides;
    }

    public void setNumSides(int numSides) {
        this.numSides = numSides;
    }
}
