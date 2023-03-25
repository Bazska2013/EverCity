package org.elte.building;

public class Factory extends Building{

    Factory(){
        price = 10;
        yearlyFee = 5;
        socialImpact = 10;
        impactRange = 10;
        width = 2;
        height = 2;
        chanceToFire = 2;
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
