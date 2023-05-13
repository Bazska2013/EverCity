package org.elte.model;

import java.util.List;
import javax.swing.*;

import org.elte.model.building.FireStation;
import org.elte.utils.Position;

import lombok.*;

@Getter
@Setter
@ToString
public class Firetruck {
  public static final int SPEED = 5; // pixels per game time tick

  private FireStation fireStation;
  private Position position;

  private Position target;

  private boolean moving = false;

  private List<Position> road;

  private Direction direction = Direction.RIGHT;

  public Firetruck(FireStation fireStation) {
    this.fireStation = fireStation;
    this.position = fireStation.getPosition();
  }

  public boolean isMoving() {
    return moving;
  }

  public void setTarget(Position target) {
    Field currentField = Game.getInstance().getField(position);
    this.target = target;
    road = currentField.getRoadToField(target);
  }

  public boolean isColliding() {
    return false;
  }

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
