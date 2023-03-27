package org.elte.model.building;

public class Road extends Building{
    Road(){
        price = 20;
        yearlyFee = 5;
        socialImpact = 0;
        impactRange = 0;
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
        return false;
    }
    public boolean isBurning() {
        return false;
    }
    public boolean isEmpty() {
        return false;
    }

}

