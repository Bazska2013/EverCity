package org.elte.model.building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Factory extends Building{

    private final String img;
    public Factory(){
        price = 10;
        yearlyFee = 5;
        socialImpact = 10;
        impactRange = 10;
        width = 2;
        height = 2;
        chanceToFire = 2;
        burning = false;
        img = "/factory.png";
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

    public Image getPic() throws IOException {
        return ImageIO.read(Objects.requireNonNull(getClass().getResource(img)));
    }
}
