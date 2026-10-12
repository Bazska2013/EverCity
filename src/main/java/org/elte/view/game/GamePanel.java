package org.elte.view.game;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

import org.elte.model.Direction;
import org.elte.model.Field;
import org.elte.model.Firetruck;
import org.elte.model.Game;
import org.elte.model.building.Building;
import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;
import org.elte.utils.Position;
import org.elte.utils.ScreenUtils;
import org.elte.view.game.gamepanel.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GamePanel extends JPanel {
  public static final int X = (int) (ScreenUtils.getScreenSize().width * 0.15);
  public static final int Y = (int) (ScreenUtils.getScreenSize().height * 0.05);
  public static final int FIELD_SIZE =
      (int) (ScreenUtils.getScreenSize().width * 0.70) / Game.getColumns();
  private static final long serialVersionUID = 1L;
  private Game game;
  private Dimension screenSize;
  private FieldInfoBox fieldInfoBox;
  private ResidentInfoBox residentInfoBox;
  public static InfoBox infoBox;
  public static GamePanel gamePanel = null;

  public static boolean zoneMode = false;

  private MouseListener mouseListener;

  private MouseMotionListener mouseMotionListener;

  private Position selectedField = null;
  /**
   * GamePanel osztály konstruktora.
   *
   * @param fieldInfoBox Az információs doboz a mezőkhöz.
   * @param infoBox Az információs doboz a játékhoz.
   * @param residentInfoBox Az információs doboz a lakosokhoz.
   */
  public GamePanel(FieldInfoBox fieldInfoBox, InfoBox infoBox, ResidentInfoBox residentInfoBox) {
    this.game = Game.getInstance();
    this.screenSize = ScreenUtils.getScreenSize();
    this.fieldInfoBox = fieldInfoBox;
    fieldInfoBox.setGamePanel(this);
    this.residentInfoBox = residentInfoBox;
    residentInfoBox.setGamePanel(this);
    this.infoBox = infoBox;
    this.mouseMotionListener = new GameMouseMotionListener(fieldInfoBox);
    this.mouseListener = new GameMouseAdapter(game, this, fieldInfoBox, infoBox, residentInfoBox);
    addMouseListener(mouseListener);
    addMouseMotionListener(mouseMotionListener);
    this.setBounds(X, Y, FIELD_SIZE * Game.getColumns(), FIELD_SIZE * Game.getRows());
    this.setVisible(true);
    gamePanel = this;
  }
  /**
   * A panel kirajzolása.
   *
   * @param g A kirajzolásra használt grafikus objektum.
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

    if (!zoneMode) {
      drawBuildings(g2d);
      game.getFiretrucks().forEach(firetruck -> drawFiretruck(g2d, firetruck));
    } else {
      drawZones(g);
    }

				/** Kirajzolja az információs doboz hátterét. */
				g.setColor(Color.blue);
				g.fillRect(screenSize.width - 150, 10, 150, 150);
		}

}
