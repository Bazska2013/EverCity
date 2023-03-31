package org.elte.model.building;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.IOException;

@Getter
@Setter
public abstract class Building {
    int price;
    int yearlyFee;
    int socialImpact;
    int impactRange;
    int width;
    int height;
    int chanceToFire;
    boolean burning;

    public abstract boolean isPlaceable();
    public abstract boolean isDeletable();
    public abstract boolean isConnected();
    public abstract boolean upgradeable();
    public abstract boolean isBurning();
    public abstract boolean isEmpty();

    /**
     * Visszaadja az adott buildinghez párosított képet
     * @return kép
     * @throws IOException
     */
    public abstract Image getPic() throws IOException;
}

