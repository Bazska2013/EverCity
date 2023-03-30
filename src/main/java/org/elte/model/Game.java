package org.elte.model;

import lombok.Getter;

import java.awt.*;
@Getter
public class Game {

    static int startx;
    static int starty;
    static int rows;
    static int columns;
    static int boxheight=32;
    static int zoneboxx;
    Field[][] fields;
    //int balance;
    //time

    public Game(){
        sizes();
        fields = new Field[rows][columns];
    }

    private void sizes(){
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        startx = (int) (screenSize.width*0.12);
        starty = (int) (screenSize.height*0.05);
        columns = (int) ((screenSize.width*0.76)/32);
        rows = (int) ((screenSize.height*0.67)/32);
        zoneboxx = (int) ((screenSize.height*0.75));
    }

    public static int getRows() { return rows; }
    public static int getColumns() { return columns; }
    public static int getStartx() { return startx; }
    public static int getStarty() { return starty; }
    public static int getBoxheight() { return boxheight; }
    public static int getZoneboxx() { return zoneboxx; }
}
