package org.elte.view.game.gamepanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.building.*;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Service;
import org.elte.model.zone.Zone;
import org.elte.utils.Position;
import org.elte.view.game.BuildablePanel;
import org.elte.view.game.GamePanel;

public class GameMouseAdapter extends MouseAdapter {
  Game game;
  GamePanel gamePanel;
  FieldInfoBox fieldInfoBox;
  InfoBox infoBox;

  public GameMouseAdapter(
      Game game, GamePanel gamePanel, FieldInfoBox fieldInfoBox, InfoBox infoBox) {
    this.fieldInfoBox = fieldInfoBox;
    this.infoBox = infoBox;
    this.game = game;
    this.gamePanel = gamePanel;
  }

  public void mouseClicked(MouseEvent e) {
    int current_x = (int) Math.floor((e.getX()) / GamePanel.FIELD_SIZE);
    int current_y = (int) Math.floor((e.getY()) / GamePanel.FIELD_SIZE);

    System.out.println("Clicked - X: " + current_x + ", Y: " + current_y);

    try {
      fieldInfoBox.setSelectedPosition(null);
      Position currentPosition = new Position(current_x, current_y);
      if (BuildablePanel.getSelectedFieldZoneType() != null) {
        placeZone(currentPosition);
        return;
      }
      if (BuildablePanel.getSelectedBuildingType() != null) {
        placeBuilding(currentPosition);
        return;
      }
      fieldInfoBox.setSelectedPosition(currentPosition);

      gamePanel.repaint();
    } catch (ArrayIndexOutOfBoundsException ex) {
      infoBox.reRender("Warning!", "Clicked outside of playing area...");
      System.out.println("Clicked outside of playing area...");
      ex.printStackTrace();
    }
  }

  private void placeBuilding(Position position) {
    Field field = game.getField(position);
    Building buildingType = new org.elte.model.building.Empty(position);
    switch (BuildablePanel.getSelectedBuildingType()) {
      case ROAD -> buildingType = new Road(position);
      case EMPTY -> field.getBuilding().delete();
      case FACTORY -> buildingType = new Factory(position);
      case FIRE_STATION -> buildingType = new FireStation(position);
      case FOREST -> buildingType = new Forest(position);
      case POLICE -> buildingType = new Police(position);
      case STADIUM -> buildingType = new Stadium(position);
    }
    if (buildingType.isPlaceable() && !buildingType.isEmpty()) {
      buildingType.create();
      BuildablePanel.setSelectedBuildingType(null);
      BuildablePanel.setSelectedFieldZoneType(null);
    } else {
      if (!buildingType.isEmpty()) {
        infoBox.reRender("Error!", buildingType.getBuildingType().name() + " can't be placed");
        System.out.println(buildingType.getBuildingType().name() + " can't be placed");
      }
    }
  }

  private void placeZone(Position position) {
    Zone newZone = null;
    switch (BuildablePanel.getSelectedFieldZoneType()) {
      case SERVICE -> newZone = new Service(position);
      case INDUSTRY -> newZone = new Industry(position);
      case RESIDENT -> newZone = new Resident(position);
    }
    if (newZone != null && newZone.isPlaceable()) {
      newZone.create();
      BuildablePanel.setSelectedBuildingType(null);
      BuildablePanel.setSelectedFieldZoneType(null);
    }
  }
}
