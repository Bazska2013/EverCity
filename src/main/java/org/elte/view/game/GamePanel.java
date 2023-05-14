package org.elte.view.game;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.building.Building;
import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;
import org.elte.utils.Position;
import org.elte.utils.ScreenUtils;
import org.elte.view.game.gamepanel.FieldInfoBox;
import org.elte.view.game.gamepanel.GameMouseAdapter;
import org.elte.view.game.gamepanel.GameMouseMotionListener;
import org.elte.view.game.gamepanel.InfoBox;

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
  public static InfoBox infoBox;
  public static GamePanel gamePanel = null;

  public static boolean zoneMode = false;

  private MouseListener mouseListener;

  private MouseMotionListener mouseMotionListener;

  private Position selectedField = null;

  public GamePanel(FieldInfoBox fieldInfoBox, InfoBox infoBox) {
    this.game = Game.getInstance();
    this.screenSize = ScreenUtils.getScreenSize();
    this.fieldInfoBox = fieldInfoBox;
    this.infoBox = infoBox;
    this.mouseMotionListener = new GameMouseMotionListener(fieldInfoBox);
    this.mouseListener = new GameMouseAdapter(game, this, fieldInfoBox, infoBox);
    addMouseListener(mouseListener);
    addMouseMotionListener(mouseMotionListener);
    this.setBounds(X, Y, FIELD_SIZE * Game.getColumns(), FIELD_SIZE * Game.getRows());
    this.setVisible(true);
    gamePanel = this;
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

    if (!zoneMode) {
      drawBuildings(g);
    } else {
      drawZones(g);
    }

    /** Kirajzolja az információs doboz hátterét. */
    g.setColor(Color.blue);
    g.fillRect(screenSize.width - 200, screenSize.height / 2 - 150, 180, 150);
  }

  private void drawBuildings(Graphics g) {
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
}
