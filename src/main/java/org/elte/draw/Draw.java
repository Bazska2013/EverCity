package org.elte.draw;

import java.awt.Color;
        import java.awt.Dimension;
        import java.awt.Graphics;
        import java.awt.Graphics2D;
        import java.awt.Point;
        import java.awt.RenderingHints;
        import java.awt.Toolkit;
        import javax.swing.JLabel;

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
        g.fillRect(160, 30, 960, 480);

        /**
         * Kirajzolja a négyzeteket.
         */
        g.setColor(Color.black);
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 15; j++) {
                g.drawRect(i * 32 + 160, j * 32 +30, 32, 32);
            }
        }

        /**
         * Kirajzolja a keretet.
         */
        g.setColor(Color.black);
        g.drawRect(160, 30, 960, 480);

        /**
         * Kirajzolja a csíkot, amin az elhelyezhető Zónák és épületek lesznek.
         */
        g.setColor(Color.GRAY);
        g.fillRect(0,screenSize.height-150 , screenSize.width, 100);

        /**
         * Kirajzolja az információs doboz hátterét.
         */
        g.setColor(Color.blue);
        g.fillRect(screenSize.width-150, 10, 150, 150);

        //repaint();
    }
}
