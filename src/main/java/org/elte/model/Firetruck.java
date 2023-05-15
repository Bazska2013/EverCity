package org.elte.model;

import java.io.Serializable;
import java.util.List;
import javax.swing.*;

import org.elte.model.building.FireStation;
import org.elte.utils.Position;

import lombok.*;

/** A tűzoltóautót reprezentáló osztály. */
@Getter
@Setter
@ToString
public class Firetruck implements Serializable {
  /** A tűzoltóautó sebessége. */
  public static final int SPEED = 5; // pixels per game time tick
  /** A tűzoltóállomás amihez tartozik a tűzoltóautó. */
  private FireStation fireStation;
  /** A tűzoltóautó pozíciója. */
  private Position position;

  private Position target;
  /** A tűzoltóautó éppen mozog-e. */
  private boolean moving = false;

  private List<Position> road;
  /** Az irány amelyikbe mindig elindul a tűzoltóautó. */
  private Direction direction = Direction.RIGHT;
  /**
   * Firetruck osztály konstruktora.
   *
   * @param fireStation a tűzoltóállomás, ahonnan a tűzoltóautó indul.
   */
  public Firetruck(FireStation fireStation) {
    this.fireStation = fireStation;
    this.position = fireStation.getPosition();
  }
  /**
   * Megadja, hogy a tűzoltóautó mozog-e.
   *
   * @return true, ha a tűzoltóautó mozog, false egyébként.
   */
  public boolean isMoving() {
    return moving;
  }
  /**
   * Beállítja a tűzoltóautó célpozícióját.
   *
   * @param target a célpozíció.
   */
  public void setTarget(Position target) {
    Field currentField = Game.getInstance().getField(position);
    this.target = target;
    road = currentField.getRoadToField(target);
  }
  /**
   * Ellenőrzi, hogy a tűzoltóautó ütközik-e.
   *
   * @return true, ha az ütközés történik, false egyébként.
   */
  public boolean isColliding() {
    return false;
  }
  /** Elindítja a tűzoltóautó mozgását. */
  public void start() {
    moving = true;
    Position start = position;
    Timer timer =
        new Timer(
            Game.getInstance().getGameTime().getDelay() / SPEED,
            e -> {
              ((Timer) e.getSource()).setDelay(Game.getInstance().getGameTime().getDelay() / SPEED);
              if (!Game.getInstance().getGameTime().isRunning()) {
                return;
              }
              if (road == null || road.isEmpty()) {
                if (position.equals(target)) {
                  // reached target, start return trip to starting position
                  target = start;
                  road = Game.getInstance().getField(position).getRoadToField(target);
                  Game.getInstance().getField(position).getBuilding().setBurning(false);
                }
              } else {
                Position nextPosition = road.remove(0);
                direction = Direction.getDirection(position, nextPosition);
                position = nextPosition;
                if (road.isEmpty()) {
                  if (target == start) {
                    ((Timer) e.getSource()).stop();
                    direction = Direction.LEFT;
                    moving = false;
                    road = null;
                    target = null;
                  }
                }
              }
            });
    timer.start();
  }
  /**
   * Ellenőrzi, hogy két objektum egyezik-e.
   *
   * @param o a másik objektum.
   * @return true, ha az objektumok egyenlőek, false egyébként.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Firetruck firetruck = (Firetruck) o;

    return fireStation.equals(firetruck.fireStation);
  }

  @Override
  public int hashCode() {
    return fireStation.hashCode();
  }
}
