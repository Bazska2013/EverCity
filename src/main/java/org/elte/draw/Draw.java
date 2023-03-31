package org.elte.draw;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import org.elte.model.Field;
import org.elte.model.Game;

public class Draw extends JLabel {
    private static final long serialVersionUID = 1L;
    Point p;

    /**
     * Elkészíti a játék grafikus kinézetét.
     * @param g
     */

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        /**
         * Megrajzolja a pálya hátterét.
         */

        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, screenSize.width,screenSize.height);

        /**
         * Megrajzolja a játékterület hátterét.
         */
        g.setColor(new Color(0, 200, 0));
        g.fillRect(Game.getStartx(),Game.getStarty(), 960, 480);

        /**
         * Kirajzolja a négyzeteket.
         */

        g.setColor(Color.black);
        for (int i = 0; i < Game.getColumns(); i++) {
            for (int j = 0; j < Game.getRows(); j++) {
                //Field f = new Field(i * 32 + 160,j * 32 +30);
                /*try {
                    Image img = ImageIO.read(getClass().getResource("grass.jpg"));
                    ((Graphics2D) g).drawImage(img,i * 32 + 160, j * 32 +30,32,32,null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }*/
                //g.drawRect(i * 32 + 160, j * 32 + 30, 32, 32);
                g.drawRect(i * 32 + Game.getStartx(), j * 32 + Game.getStarty(), Game.getBoxheight(), Game.getBoxheight());
            }
        }

        /**
         * Kirajzolja a keretet.
         */
        g.setColor(Color.black);
        g.drawRect(Game.getStartx(), Game.getStarty(), Game.getBoxheight()*Game.getColumns(), Game.getBoxheight()*Game.getRows());

        /**
         * Kirajzolja a csíkot, amin az elhelyezhető Zónák és épületek lesznek.
         */

        g.setColor(Color.GRAY);
        g.fillRect(0,Game.getZoneboxx() , screenSize.width, screenSize.height-Game.getZoneboxx() );

        /**
         * Kirajzolja az információs doboz hátterét.
         */
        g.setColor(Color.blue);
        g.fillRect(screenSize.width-150, 10, 150, 150);

        //repaint();
    }
}
