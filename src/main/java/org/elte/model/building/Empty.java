package org.elte.model.building;

class Empty extends Building {

    Empty(){
        price = 0;
        yearlyFee = 0;
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
        return false;
    }
    public boolean isConnected(){
        return false;
    }
    public boolean upgradeable() {
        return false;
    }
    public boolean isBurning() {
        return false;
    }
    public boolean isEmpty() {
        return true;
    }

}

