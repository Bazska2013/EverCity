package org.elte.model.building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Stadium extends Building{
    private final String img;

    public Stadium(){
        price = 100;
        yearlyFee = 20;
        socialImpact = 20;
        impactRange = 10;
        width = 2;
        height = 2;
        chanceToFire = 1;
        burning = false;
        img = "/football-stadion.png";
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

