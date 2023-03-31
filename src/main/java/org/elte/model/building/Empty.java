package org.elte.model.building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Empty extends Building {
    private final String img;

    public Empty(){
        price = 0;
        yearlyFee = 0;
        socialImpact = 0;
        impactRange = 0;
        width = 1;
        height = 1;
        chanceToFire = 0;
        burning = false;
        img = "/grass.png";
    }

    public boolean isPlaceable() {
        return true;
    }
    public boolean isDeletable() {
        return false;
    }
    public boolean isConnected(){
        return false;
    }
    public boolean upgradeable() {
        return false;
    }
    public boolean isBurning() {
        return false;
    }
    public boolean isEmpty() {
        return true;
    }

    public Image getPic() throws IOException {
        return ImageIO.read(Objects.requireNonNull(getClass().getResource(img)));
    }

}

