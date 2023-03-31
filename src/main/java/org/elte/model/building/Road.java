package org.elte.model.building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Road extends Building{

    private final String img;
    public Road(){
        price = 20;
        yearlyFee = 5;
        socialImpact = 0;
        impactRange = 0;
        width = 1;
        height = 1;
        chanceToFire = 0;
        burning = false;
        img = "/road.png";
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

    public Image getPic() throws IOException {
        return ImageIO.read(Objects.requireNonNull(getClass().getResource(img)));
    }
}

