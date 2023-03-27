package org.elte.model.zone;

import org.elte.model.people.Citizen;

import java.util.List;

public class Resident extends Zone {

    protected List<Citizen> citizens;

    public Resident(int capacity, int hapiness, int tax, int price, int level, List<Citizen> citizens) {
        this.capacity = capacity;
        this.citizens = citizens;
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
