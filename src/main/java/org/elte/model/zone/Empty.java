package org.elte.model.zone;

import java.util.List;

public class Empty extends Zone {

    public Empty(int price) {
        this.capacity = 0;
        this.hapiness = 0;
        this.tax = 0;
        this.level = 0;
        this.price = price;
    }

    @Override
    public boolean isDeletable() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

}