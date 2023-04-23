package org.elte.model.zone;

import org.elte.view.MainFrame;

import java.util.List;

public class Service extends Zone {

    public Service(int capacity, int hapiness, int tax, int price, int level) {
        this.capacity = capacity;
        this.hapiness = hapiness;
        this.tax = tax;
        this.level = level;
        this.price = price;
        payment();
    }

    @Override
    public boolean isDeletable() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    @Override
    public int getTax() {
        return tax*capacity;
    }
    @Override
    public void payment(){
        MainFrame.SetBalance(MainFrame.GetBalance()-price);
    }

}
