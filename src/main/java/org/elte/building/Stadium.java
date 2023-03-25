package org.elte.building;

public class Stadium extends Building{
    Stadium(){
        price = 100;
        yearlyFee = 20;
        socialImpact = 20;
        impactRange = 10;
        width = 2;
        height = 2;
        chanceToFire = 1;
        burning = false;
    }

    public boolean isPlaceable() {
        return true;
    }
    public boolean isDeletable() {
        return true;
    }
    public boolean isConnected(){
        return true;
    }
    public boolean upgradeable() {
        return false;
    }
    public boolean isBurning() {
        return false;
    }
    public boolean isEmpty() {
        return false;
    }

}

