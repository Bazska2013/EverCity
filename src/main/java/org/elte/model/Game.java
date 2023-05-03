package org.elte.model;

import java.awt.*;
import java.util.Optional;

import org.elte.model.building.House;
import org.elte.model.building.Road;
import org.elte.model.building.ServiceBuilding;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.Position;
import org.elte.view.game.GamePanel;

import lombok.Getter;

@Getter
public class Game {
  private static Game INSTANCE = null;
  static int rows = 16;
  static int columns = 32;
  private int balance = 20000;
  public Field[][] fields;
  // int balance;
  private final GameTime gameTime;

  private Game() {
    fields = new Field[rows][columns];
    fillFields();
    fields[0][2].setBuilding(new Road(new Position(2, 0)));
    fields[1][2].setBuilding(new Road(new Position(2, 1)));
    gameTime =
        new GameTime(
            e -> {
              tryToPlaceCitizenHouse();
              tryToPlaceServiceBuilding();
              if (getGameTime().overAYear()) {
                balance += yearlyTax();
                balance -= yearlyFee();
              }
            });
    gameTime.setTimerSpeed(TimerSpeed.ONE);
  }

  public void fillFields() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        fields[i][j] = new Field(new Position(j, i));
      }
    }
  }

  public static int getRows() {
    return rows;
  }

  public static int getColumns() {
    return columns;
  }

  public Field getField(Position position) {
    if (position != null) return fields[position.y()][position.x()];
    return null;
  }

  private void tryToPlaceCitizenHouse() {
    this.findZonePositionNearStreet(FieldTypes.RESIDENT)
        .ifPresent(
            position -> {
              if ((int) (Math.random() * 2) == 1) {
                this.getField(position).setBuilding(new House(position));
              }
            });
  }

  private void tryToPlaceServiceBuilding() {
    this.findZonePositionNearStreet(FieldTypes.SERVICE)
        .ifPresent(
            position -> {
              if ((int) (Math.random() * 3) == 1) {
                this.getField(position).setBuilding(new ServiceBuilding(position));
              }
            });
  }

  public Optional<Position> findZonePositionNearStreet(FieldTypes zoneType) {
    for (int i = 0; i < fields.length; i++) {
      for (int j = 0; j < fields[i].length; j++) {
        if (fields[i][j].getBuilding().isRoad()) {
          if (i + 1 < fields.length
              && fields[i + 1][j].getBuilding().isEmpty()
              && fields[i + 1][j].getZone().getZoneType() == zoneType) {
            return Optional.of(new Position(j, i + 1));
          }
          if (i - 1 >= 0
              && fields[i - 1][j].getBuilding().isEmpty()
              && fields[i - 1][j].getZone().getZoneType() == zoneType) {
            return Optional.of(new Position(j, i - 1));
          }
          if (j + 1 < fields[i].length
              && fields[i][j + 1].getBuilding().isEmpty()
              && fields[i][j + 1].getZone().getZoneType() == zoneType) {
            return Optional.of(new Position(j + 1, i));
          }
          if (j - 1 >= 0
              && fields[i][j - 1].getBuilding().isEmpty()
              && fields[i][j - 1].getZone().getZoneType() == zoneType) {
            return Optional.of(new Position(j - 1, i));
          }
        }
      }
    }
    return Optional.empty();
  }

  public int yearlyFee() {
    int fees = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        fees += fields[i][j].getFees();
      }
    }
    return fees;
  }

  public static Game getInstance() {
    if (INSTANCE == null) INSTANCE = new Game();

    return INSTANCE;
  }

  public int yearlyTax() {
    int fees = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        fees += fields[i][j].getTaxes();
      }
    }
    GamePanel.infoBox.reRender("Attention!", "Yearly TAX payed", Color.BLUE);
    return fees;
  }
}
