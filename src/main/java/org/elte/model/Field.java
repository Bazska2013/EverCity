package org.elte.model;

import org.elte.model.building.Building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Field {
    Building buildings;
    //Zone zone;
    int x;
    int y;

    public Field(int x, int y){
        //zone = üres zone;
        //buildings = ures building;
        this.x=x;
        this.y=y;
    }

    public Image getImage() throws IOException {
        Image img = ImageIO.read(getClass().getResource("grass.jpg"));
        return img;

        //return zone.getPic();
    }

}
