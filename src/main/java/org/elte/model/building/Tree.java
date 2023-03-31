package org.elte.model.building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

class Tree extends Building {

    private final String img;
    int size;

    public Tree() {
        price = 10;
        yearlyFee = 5;
        socialImpact = 10;
        impactRange = 5;
        width = 1;
        height = 1;
        chanceToFire = 1;
        burning = false;
        this.size = 1;
        img = "/forest.png";
    }

    public boolean isPlaceable() {
        return true;
    }

    public boolean isDeletable() {
        return true;
    }

    public boolean isConnected() {
        return false;
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


