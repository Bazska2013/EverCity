package org.elte.model.zone;

import org.elte.view.MainFrame;

import java.util.List;

public class Empty extends Zone {

    public Empty(int price) {
        this.capacity = 0;
        this.hapiness = 0;
        this.tax = 0;
        this.level = 0;
        this.price = price;
        payment();
    }

    @Override
    public boolean isDeletable() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
    @Override
    public int getTax() {
        return tax*capacity;
    }
    @Override
    public void payment(){
        MainFrame.SetBalance(MainFrame.GetBalance()+price);
    }

}