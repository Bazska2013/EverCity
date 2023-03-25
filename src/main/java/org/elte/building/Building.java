package org.elte.building;

public abstract class Building {
    int price;
    int yearlyFee;
    int socialImpact;
    int impactRange;
    int width;
    int height;
    int chanceToFire;
    boolean burning;

    abstract boolean isPlaceable();
    abstract boolean isDeletable();
    abstract boolean isConnected();
    abstract boolean upgradeable();
    abstract boolean isBurning();
    abstract boolean isEmpty();
}

