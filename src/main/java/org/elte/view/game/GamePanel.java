package org.elte.view.game;

import org.elte.model.Game;
import org.elte.utils.ResourceLoader;
import org.elte.utils.ScreenUtils;
import org.elte.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JLabel {
  private static final long serialVersionUID = 1L;
  private Game game;
  private Dimension screenSize;
  Point p;

  public GamePanel() {
    this.game = Game.getInstance();
    this.screenSize = ScreenUtils.getScreenSize();
    this.setBounds(0, 0, screenSize.width, screenSize.height);
    this.setVisible(true);
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

    /** Megrajzolja a játékterület hátterét. */
    g.setColor(new Color(0, 200, 0));
    g.fillRect(MainFrame.START_X, MainFrame.START_Y, 960, 480);

    /** Kirajzolja a négyzeteket. */
    g.setColor(Color.black);
    for (int i = 0; i < Game.getColumns(); i++) {
      for (int j = 0; j < Game.getRows(); j++) {
        try {
          g.drawImage(ResourceLoader.loadImage("grass.png"), i * 32 + MainFrame.START_X, j * 32 + MainFrame.START_Y, Game.getBoxheight(), Game.getBoxheight(), null);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        // Field f = new Field(i * 32 + 160,j * 32 +30);
								/*try {
										Image img = ImageIO.read(getClass().getResource("grass.jpg"));
										((Graphics2D) g).drawImage(img,i * 32 + 160, j * 32 +30,32,32,null);
								} catch (IOException e) {
										throw new RuntimeException(e);
								}*/
        // g.drawRect(i * 32 + 160, j * 32 + 30, 32, 32);
        g.drawRect(
                i * 32 + MainFrame.START_X,
                j * 32 + MainFrame.START_Y,
                Game.getBoxheight(),
                Game.getBoxheight());
      }
    }

    /** Kirajzolja a keretet. */
    g.setColor(Color.black);
    g.drawRect(
            MainFrame.START_X,
            MainFrame.START_Y,
            Game.getBoxheight() * Game.getColumns(),
            Game.getBoxheight() * Game.getRows());

    /** Kirajzolja a csíkot, amin az elhelyezhető Zónák és épületek lesznek. */


    /** Kirajzolja az információs doboz hátterét. */
    g.setColor(Color.blue);
    g.fillRect(screenSize.width - 150, 10, 150, 150);

    // repaint();
  }
}
