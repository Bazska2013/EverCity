package org.elte.model.building;

import java.awt.*;
import java.util.ArrayList;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.Position;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Building {
  Position position;
  int price = 0;
  int health = 100;
  int yearlyFee;
  int socialImpact = 0;
  int impactRange = 0;
  int width = 1;
  int height = 1;
  double chanceToFire = 0;
  boolean burning = false;
  BuildingTypes buildingType;
  ArrayList<Position> fieldPositions = new ArrayList<>();

  public boolean isPlaceable() {
    return hasEnoughSpace();
  }

  public boolean canBurn() {
    return chanceToFire > 0;
  }

  public abstract boolean isConnected();

  public abstract boolean upgradeable();

  public boolean isBurning() {
    return burning;
  }
  ;

  public boolean isFireStation() {
    return false;
  }

  public abstract boolean isEmpty();

  public boolean isRoad() {
    return false;
  }

  public boolean hasEnoughSpace() {
    return hasEnoughSpace(FieldTypes.EMPTY);
  }

  public boolean hasEnoughSpace(FieldTypes fieldTypes) {
    Game game = Game.getInstance();
    Field field = game.getField(position);
    if (field == null) {
      System.out.println("field is null");
      return false;
    }
    if (position.x() < 0
        || position.x() + width > Game.getColumns()
        || position.y() < 0
        || position.y() + height > Game.getRows()) {
      System.out.println("out of bound");
      return false;
    }

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Field currentField = game.getField(new Position(position.x() + j, position.y() + i));
        if (!currentField.getBuilding().isEmpty()
            || !(currentField.getZone().getZoneType() == fieldTypes)) {
          System.out.println("Not empty or zonetype not fit");
          return false;
        }
      }
    }
    return true;
  }

  public boolean hasRoadAtLeastOneSide() {
    Game game = Game.getInstance();
    for (int i = position.y() - 1; i <= position.y() + height; i++) {
      for (int j = position.x() - 1; j <= position.x() + width; j++) {
        if (i == position.y() - 1
            || i == position.y() + height
            || j == position.x() - 1
            || j == position.x() + width) {
          // check if the field is within the game boundaries
          if (i >= 0 && i < Game.getRows() && j >= 0 && j < Game.getColumns()) {
            Field field = game.getField(new Position(j, i));
            if (field.getBuilding() instanceof Road) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  public boolean hasBuildingAtLeastOneSide() {
    Game game = Game.getInstance();
    for (int i = position.y() - 1; i <= position.y() + height; i++) {
      for (int j = position.x() - 1; j <= position.x() + width; j++) {
        if (i == position.y() - 1
            || i == position.y() + height
            || j == position.x() - 1
            || j == position.x() + width) {
          // check if the field is within the game boundaries
          if (i >= 0 && i < Game.getRows() && j >= 0 && j < Game.getColumns()) {
            Field field = game.getField(new Position(j, i));
            if (field.getBuilding() instanceof Factory
                || field.getBuilding() instanceof FireStation
                || field.getBuilding() instanceof House
                || field.getBuilding() instanceof Police
                || field.getBuilding() instanceof Stadium) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  public void create() {
    if (isPlaceable()) {
      Game game = Game.getInstance();
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          Position currentPosition = new Position(position.x() + j, position.y() + i);
          Field currentField = game.getField(currentPosition);
          fieldPositions.add(currentPosition);
          currentField.setBuilding(this);
          game.setBalance(game.getBalance() - price);
        }
      }
    }
  }

  public void delete() {
    if (isRoad()) {
      if (!hasBuildingAtLeastOneSide()) {
        Game game = Game.getInstance();
        fieldPositions.forEach(
            pos -> {
              Field currentField = game.getField(pos);
              currentField.setBuilding(new Empty(pos));
            });
        game.getField(position).setBuilding((new Empty(position)));
        if (!game.everythingStillConnected()) {
          fieldPositions.forEach(
              pos -> {
                Field currentField = game.getField(pos);
                currentField.setBuilding(new Road(pos));
              });
          game.getField(position).setBuilding((new Road(position)));
        }
      }
    } else {
      Game game = Game.getInstance();
      fieldPositions.forEach(
          pos -> {
            Field currentField = game.getField(pos);
            currentField.setBuilding(new Empty(pos));
          });
      game.getField(position).setBuilding((new Empty(position)));
    }
  }

  public abstract int getTax();
}
