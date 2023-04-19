package org.elte.model.zone;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public abstract class Zone {
    protected int price;
    protected int capacity;
    protected int hapiness;
    protected int tax;
    protected int level;

    public abstract boolean isDeletable();
    public abstract boolean isEmpty();

    public abstract int getTax();

}
