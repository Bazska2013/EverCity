package org.elte.model.zone;

import java.util.List;

public class Industry extends Zone {

    public Industry(int capacity, int hapiness, int tax, int price, int level){
        this.capacity = capacity;
        this.hapiness = hapiness;
        this.tax = tax;
        this.level = level;
        this.price = price;
    }

    @Override
    public boolean isDeletable() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
