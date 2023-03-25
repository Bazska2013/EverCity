package org.elte.building;

public class FireStation extends Building{
    FireStation(){
        price = 30;
        yearlyFee = 10;
        socialImpact = 5;
        impactRange = 30;
        width = 1;
        height = 1;
        chanceToFire = 0;
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
        return true;
    }
    public boolean isBurning() {
        return false;
    }
    public boolean isEmpty() {
        return false;
    }

}

