package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class FireStation extends Building{
    public FireStation(){
        buildingType = BuildingTypes.FIRE_STATION;
        price = 30;
        yearlyFee = 10;
        socialImpact = 5;
        impactRange = 30;
        width = 1;
        height = 1;
        chanceToFire = 0;
        burning = false;
        imageResource = ImageResource.FIRE_STATION;
    }

    public boolean isPlaceable() {
        return true;
    }
    public boolean isDeletable() {
        return true;
    }
    public boolean isConnected(){
        return true;
    }
    public boolean upgradeable() {
        return true;
    }
    public boolean isBurning() {
        return false;
    }
    public boolean isEmpty() {
        return false;
    }

}

