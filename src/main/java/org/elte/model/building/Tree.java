package org.elte.model.building;

class Tree extends Building {

    int size;

    Tree() {
        price = 10;
        yearlyFee = 5;
        socialImpact = 10;
        impactRange = 5;
        width = 1;
        height = 1;
        chanceToFire = 1;
        burning = false;
        this.size = 1;
    }

    public boolean isPlaceable() {
        return true;
    }

    public boolean isDeletable() {
        return true;
    }

    public boolean isConnected() {
        return false;
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


