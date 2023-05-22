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
    g.fillRect(screenSize.width - 200, screenSize.height / 2 - 150, 180, 150);
  }
  /**
   * Az épületek kirajzolása.
   *
   * @param g A kirajzolásra használt grafikus objektum.
   */
  private void drawBuildings(Graphics2D g) {
    ArrayList<Position> positionsToSkip = new ArrayList<>();
    for (int i = 0; i < Game.getColumns(); i++) {
      for (int j = 0; j < Game.getRows(); j++) {
        Position position = new Position(i, j);
        if (positionsToSkip.contains(position)) {
          continue;
        }
        Field field = game.getField(position);
        Building building = field.getBuilding();
        int x = i * FIELD_SIZE;
        int y = j * FIELD_SIZE;
        int width = building.getWidth() * FIELD_SIZE;
        int height = building.getHeight() * FIELD_SIZE;
        g.drawImage(ImageResource.getImageOfClass(building.getClass()), x, y, width, height, null);
        if (field.getBuilding().getBuildingType() == BuildingTypes.HOUSE_2) {
          g.drawImage(ImageResource.HOUSE_LV2, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.HOUSE_3) {
          g.drawImage(ImageResource.HOUSE_LV3, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.SERVICE_2) {
          g.drawImage(ImageResource.SERVICE_2, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.SERVICE_3) {
          g.drawImage(ImageResource.SERVICE_3, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.INDUSTRY_2) {
          g.drawImage(ImageResource.INDUSTRY_2, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.INDUSTRY_3) {
          g.drawImage(ImageResource.INDUSTRY_3, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.FOREST2) {
          g.drawImage(ImageResource.FOREST2, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.FOREST3) {
          g.drawImage(ImageResource.FOREST3, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.FOREST4) {
          g.drawImage(ImageResource.FOREST4, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.FOREST5) {
          g.drawImage(ImageResource.FOREST5, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.FOREST6) {
          g.drawImage(ImageResource.FOREST6, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.FOREST7) {
          g.drawImage(ImageResource.FOREST7, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.FOREST8) {
          g.drawImage(ImageResource.FOREST8, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.FOREST9) {
          g.drawImage(ImageResource.FOREST9, x, y, width, height, null);
        }
        if (field.getBuilding().getBuildingType() == BuildingTypes.FOREST10) {
          g.drawImage(ImageResource.FOREST10, x, y, width, height, null);
        }
        if (field.getBuilding().isBurning()) {
          g.drawImage(ImageResource.FIRE, x, y, width, height, null);
        }
        if (fieldInfoBox.getSelectedPosition() != null
            && fieldInfoBox.getSelectedPosition().equals(position)) {
          g.setColor(Color.red);
          g.drawRect(x, y, width - 2, height - 2);
        }
        for (int k = 0; k < building.getWidth(); k++) {
          for (int l = 0; l < building.getHeight(); l++) {
            positionsToSkip.add(new Position(position.x() + l, position.y() + k));
          }
        }
      }
    }
  }
  /**
   * A zónák kirajzolása.
   *
   * @param g A kirajzolásra használt grafikus objektum.
   */
  private void drawZones(Graphics g) {
    g.setColor(Color.black);
    for (int i = 0; i < Game.getColumns(); i++) {
      for (int j = 0; j < Game.getRows(); j++) {
        g.drawImage(
            ImageResource.getZoneImageOfClass(
                game.getField(new Position(i, j)).getZone().getClass()),
            i * FIELD_SIZE,
            j * FIELD_SIZE,
            FIELD_SIZE,
            FIELD_SIZE,
            null);
      }
    }
  }
  /**
   * A tűzoltók kirajzolása.
   *
   * @param g2d A kirajzolásra használt grafikus objektum.
   * @param firetruck A kirajzolandó tűzoltó jármű.
   */
  public void drawFiretruck(Graphics2D g2d, Firetruck firetruck) {
    double angle = 0; // default angle (no rotation)
    Direction direction = firetruck.getDirection();
    int x = firetruck.getPosition().x() * GamePanel.FIELD_SIZE;
    int y = firetruck.getPosition().y() * GamePanel.FIELD_SIZE;
    if (direction != null) {
      switch (direction) {
        case UP:
          angle = Math.PI / 2;
          break;
        case DOWN:
          angle = -Math.PI / 2;
          break;
        case RIGHT:
          angle = Math.PI;
          Image flippedImage = flip((BufferedImage) ImageResource.FIRETRUCK);
          g2d.drawImage(
              flippedImage,
              x + GamePanel.FIELD_SIZE,
              y + GamePanel.FIELD_SIZE,
              -GamePanel.FIELD_SIZE,
              -GamePanel.FIELD_SIZE,
              null);
          break;
        default:
          angle = 0;
          break;
      }
      g2d.rotate(angle, x + (FIELD_SIZE / 2), y + (FIELD_SIZE / 2));
    }
    if (direction != Direction.RIGHT)
      g2d.drawImage(
          ImageResource.FIRETRUCK, x, y, GamePanel.FIELD_SIZE, GamePanel.FIELD_SIZE, null);
    if (direction != null) g2d.rotate(-angle, x + (FIELD_SIZE / 2), y + (FIELD_SIZE / 2));
  }
  /**
   * Kép elforgatása.
   *
   * @param image Az elforgatandó kép.
   * @return Az elforgatott kép.
   */
  public static BufferedImage flip(BufferedImage image) {
    int w = image.getWidth();
    int h = image.getHeight();
    BufferedImage flippedImage = new BufferedImage(w, h, image.getType());
    Graphics2D g = flippedImage.createGraphics();
    g.drawImage(image, 0, 0, w, h, 0, h, w, 0, null);
    g.dispose();
    return flippedImage;
  }
}
