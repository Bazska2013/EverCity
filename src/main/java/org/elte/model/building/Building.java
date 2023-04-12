package org.elte.model.building;

import lombok.Getter;
import lombok.Setter;
import org.elte.model.enums.BuildingTypes;

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

    Image imageResource;
    BuildingTypes buildingType;

    public abstract boolean isPlaceable();
    public abstract boolean isDeletable();
    public abstract boolean isConnected();
    public abstract boolean upgradeable();
    public abstract boolean isBurning();
    public abstract boolean isEmpty();

    /**
     * Visszaadja az adott buildinghez párosított képet
     * @return kép
     */
    public Image getImageResource() {return imageResource;};
}

