package org.elte.model.building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Police extends Building{
    private final String img;
    public Police(){
        price = 30;
        yearlyFee = 10;
        socialImpact = 5;
        impactRange = 30;
        width = 1;
        height = 1;
        chanceToFire = 1;
        burning = false;
        img = "/police.png";
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
