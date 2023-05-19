package org.elte.view.game.gamepanel;

import java.awt.*;
import javax.swing.*;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.zone.Empty;
import org.elte.model.zone.Road;
import org.elte.utils.Position;
import org.elte.utils.ScreenUtils;
import org.elte.view.Localization;
import org.elte.view.common.buttons.TextButton;
import org.elte.view.game.GamePanel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldInfoBox extends JPanel {

  public static final int X = 0;
  public static final int Y = (int) (ScreenUtils.getScreenSize().height * 0.10);
  public static final int HEIGHT = (int) (ScreenUtils.getScreenSize().height * 0.50);
  public static final int WIDTH = (int) (ScreenUtils.getScreenSize().width * 0.15) - 10;
  private Game game;
  private GamePanel gamePanel;
  private Position selectedPosition;
  private JLabel zoneTypeLabel;
  private JLabel buildingTypeLabel;
  private JLabel positionXLabel;
  private JLabel positionYLabel;
  private JLabel citizenNumber;
  private JButton deleteButton;
  private JButton upgradeButton;

  public FieldInfoBox() {
    super();
    this.game = Game.getInstance();
    this.setBounds(X, Y, WIDTH, HEIGHT);
    this.setBorder(BorderFactory.createLineBorder(Color.black, 3));
    this.setLayout(new GridLayout(7, 1)); // add one more row for the button
    this.setBackground(Color.gray);
    this.setVisible(true);

    Font labelFont = new Font("Arial", Font.PLAIN, 14);

    zoneTypeLabel = new JLabel();
    zoneTypeLabel.setForeground(Color.white);
    zoneTypeLabel.setFont(labelFont);
    this.add(zoneTypeLabel);

    buildingTypeLabel = new JLabel();
    buildingTypeLabel.setForeground(Color.white);
    buildingTypeLabel.setFont(labelFont);
    this.add(buildingTypeLabel);

    positionXLabel = new JLabel();
    positionXLabel.setForeground(Color.white);
    positionXLabel.setFont(labelFont);
    this.add(positionXLabel);

    positionYLabel = new JLabel();
    positionYLabel.setForeground(Color.white);
    positionYLabel.setFont(labelFont);
    this.add(positionYLabel);

    citizenNumber = new JLabel();
    citizenNumber.setForeground(Color.white);
    citizenNumber.setFont(labelFont);
    this.add(citizenNumber);

    deleteButton = new TextButton(Localization.getProp().getProperty("delete"));
    deleteButton.setBackground(Color.RED);
    this.add(deleteButton);

    upgradeButton = new TextButton(Localization.getProp().getProperty("upgrade"));
    upgradeButton.setBackground(Color.decode("#339157"));

    upgradeButton.addActionListener(
        e -> {
          game.getField(selectedPosition).getZone().upgrade();
          setSelectedPosition(selectedPosition);
        });
    deleteButton.addActionListener(
        e -> {
          game.getField(selectedPosition).getBuilding().delete();
          setSelectedPosition(selectedPosition);
          game.getField(selectedPosition).setZone(new Empty(selectedPosition));
        });

    reRender();
  }

  public void reRender() {
    if (selectedPosition != null) {
      zoneTypeLabel.setText(
          Localization.getProp().getProperty("zone_type_title")
              + " "
              + game.getField(selectedPosition).getZone().getZoneType().name());
      buildingTypeLabel.setText(
          Localization.getProp().getProperty("building_type_title")
              + " "
              + game.getField(selectedPosition).getBuilding().getBuildingType().name());
      positionXLabel.setText(
          Localization.getProp().getProperty("zone_position_x") + " " + selectedPosition.x());
      positionYLabel.setText(
          Localization.getProp().getProperty("zone_position_y") + " " + selectedPosition.y());
      citizenNumber.setText(
          Localization.getProp().getProperty("number_of_citizens")
              + ": "
              + game.getField(selectedPosition).getZone().getCitizenNumber());
      deleteButton.setVisible(true); // enable the button when a position is selected

      Field selectedField = game.getField(selectedPosition);

      if (selectedField.getZone() instanceof Empty || selectedField.getZone() instanceof Road) {
        if (selectedField.getBuilding() instanceof org.elte.model.building.Empty) {
          this.remove(deleteButton);
          this.remove(upgradeButton);
          this.setLayout(new GridLayout(5, 1));
        } else {
          this.add(deleteButton);
          this.remove(upgradeButton);
          this.setLayout(new GridLayout(6, 1));
        }
      } else {
        if (!(selectedField.getBuilding() instanceof org.elte.model.building.Empty)) {
          this.add(upgradeButton);
          this.add(deleteButton);
          this.setLayout(new GridLayout(7, 1));
          upgradeButton.setVisible(true);
        } else {
          this.remove(deleteButton);
          this.remove(upgradeButton);
          this.setLayout(new GridLayout(5, 1));
        }
      }
    } else {
      zoneTypeLabel.setText("");
      buildingTypeLabel.setText("");
      positionXLabel.setText("");
      positionYLabel.setText("");
      citizenNumber.setText("");
      deleteButton.setVisible(false); // disable the button when no position is selected
      upgradeButton.setVisible(false);
    }
  }

  public void setGamePanel(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  public void setSelectedPosition(Position position) {
    this.selectedPosition = position;
    reRender();
  }
}
