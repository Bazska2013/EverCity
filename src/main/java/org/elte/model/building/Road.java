package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Road extends Building{

    public Road(){
        buildingType = BuildingTypes.ROAD;
        price = 20;
        yearlyFee = 5;
        socialImpact = 0;
        impactRange = 0;
        width = 1;
        height = 1;
        chanceToFire = 0;
        burning = false;
        imageResource = ImageResource.ROAD;
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
        return false;
    }
    public boolean isBurning() {
        return false;
    }
    public boolean isEmpty() {
        return false;
    }

}

