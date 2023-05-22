package org.elte.model.building;

import java.awt.*;
import java.util.ArrayList;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.Position;
import org.elte.view.MainFrame;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/** Az absztrakt Building osztály reprezentál egy épületet a játékban. */
public abstract class Building {
  /** Az épület pozíciója. */
  Position position;
  /** Az ár, amelyet fizetni kell az épület létrehozásához. */
  int price = 0;
  /** Az épület egészségi állapota. */
  int health = 100;
  /** Az éves fenttartási díj. */
  int yearlyFee;
  /** Mennyivel befolyásolja a környezetét. */
  int socialImpact = 0;
  /** Mekkora hatókörben befolyásolja a környezetét. */
  int impactRange = 0;
  /** Az épület szélessége. */
  int width = 1;
  /** Az épület magassága. */
  int height = 1;
  /** Esély, hogy kigyulladjon az épület. */
  double chanceToFire = 0;
  /** Esély, hogy összeomoljon az épület. */
  double chanceToCollapse = 0;
  /** Tárolja, hogy éppen ég-e az épület. */
  boolean burning = false;
  /** Az épület típusa. */
  BuildingTypes buildingType;

  ArrayList<Position> fieldPositions = new ArrayList<>();
  /**
   * Megadja, hogy az épület elhelyezhető-e.
   *
   * @return true, ha az épület elhelyezhető, különben false.
   */
  public boolean isPlaceable() {
    return hasEnoughSpace();
  }
  /** Visszaadja, hogy az épület ki tud-e gyulladni. */
  public boolean canBurn() {
    return chanceToFire > 0;
  }

  public abstract boolean isConnected();
  /**
   * Megadja, hogy az épület fejleszthető-e.
   *
   * @return true, ha az épület fejleszthető, különben false.
   */
  public abstract boolean upgradeable();
  /** Visszaadja, hogy az épület ég-e. */
  public boolean isBurning() {
    return burning;
  }
  /**
   * Megadja, hogy az épület tűzoltóállomás-e.
   *
   * @return true, ha az épület tűzoltóállomas, különben false.
   */
  public boolean isFireStation() {
    return false;
  }
  /**
   * Megadja, hogy van-e épület a zónán.
   *
   * @return true, ha nincs épület, különben false.
   */
  public abstract boolean isEmpty();
  /**
   * Megadja, hogy az épület út-e.
   *
   * @return true, ha az épület út, különben false.
   */
  public boolean isRoad() {
    return false;
  }
  /** Megnézi van-e elegendő hely nagyobb épületek lehelyezéséhez. */
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
  /** Visszaadja, hogy az épület egyik oldalán van-e út. */
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
  /** Visszaadja, hogy az épület egyik oldalán van-e épület. */
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
  /** Létrehozza az épületet, ha lehelyezhető. */
  public void create(String s) {
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
      for (int i = Math.max(0, position.x() - impactRange);
          i <= Math.min(position.x() + impactRange, game.getFields().length - 1);
          i++) {
        for (int j = Math.max(0, position.y() - impactRange);
            j <= Math.min(position.y() + impactRange, game.getFields()[i].length - 1);
            j++) {
          game.getField(new Position(j, i)).getImpactedBy().add(this);
        }
      }
      game.setBalance(game.getBalance() - price);
      game.getAllCosts().add(s + "," + price);
    }
  }
  /** Törli az épületet. */
  public void delete() {
    Game game = Game.getInstance();
    Building building = game.getField(position).getBuilding();

    if (building instanceof Rubble) {
      Game.getInstance().setBalance(Game.getInstance().getBalance() - 5);
    } else if (building instanceof Police || building instanceof Stadium) {
      Game.getInstance()
          .setBalance(
              Game.getInstance().getBalance()
                  + Math.round(game.getField(position).getBuilding().getPrice() / 2.f));
    } else {
      Game.getInstance()
          .setBalance(
              Game.getInstance().getBalance()
                  - Math.round(game.getField(position).getBuilding().getPrice() / 2.f));
    }

    if (isRoad()) {
      if (!hasBuildingAtLeastOneSide()) {
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
    } else if (!(game.getField(position).getZone() instanceof org.elte.model.zone.Empty)) {
      fieldPositions.forEach(
          pos -> {
            Field currentField = game.getField(pos);
            currentField.removeCitizens();
            currentField.setBuilding(new Empty(pos));
            MainFrame mainFrame = MainFrame.getInstance();
            mainFrame.repaintCitizenNumber();
          });
      game.getField(position).removeCitizens();
      MainFrame mainFrame = MainFrame.getInstance();
      mainFrame.repaintCitizenNumber();
      game.getField(position).setBuilding((new Empty(position)));
    } else {
      fieldPositions.forEach(
          pos -> {
            Field currentField = game.getField(pos);
            currentField.setBuilding(new Empty(pos));
          });
      game.getField(position).setBuilding((new Empty(position)));
    }
  }
  /**
   * Visszaadja, hogy a épület éves fenttartási díjját.
   *
   * @return az épületre vonatkozó éves fenttartási díj
   */
  public abstract int getTax();
}
