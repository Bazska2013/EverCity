package org.elte.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.elte.model.building.Building;
import org.elte.model.building.Empty;
import org.elte.model.zone.Zone;
import org.elte.utils.BFSRunner;
import org.elte.utils.Position;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Field {
  Building building;
  private Zone zone;
  private int height = 1;
  private int width = 1;
  Position position;

  public Field(Position position) {
    zone = new org.elte.model.zone.Empty(position);
    building = new Empty(position);
    this.position = position;
  }

  public void setZone(Zone zone) {
    this.zone = zone;
  }

  public int getTaxes() {
    return zone.getTax();
  }

  public int getFees() {
    return building.getTax();
  }

  public ArrayList<Field> getNeighbors() {
    Game game = Game.getInstance();
    ArrayList<Field> neighbors = new ArrayList<>();
    int rows = Game.getRows();
    int cols = Game.getColumns();
    int x = position.x();
    int y = position.y();
    if (y > 0) {
      neighbors.add(game.getField(new Position(x, y - 1)));
    }
    if (y < cols - 1) {
      neighbors.add(game.getField(new Position(x, y + 1)));
    }
    if (x > 0) {
      neighbors.add(game.getField(new Position(x - 1, y)));
    }
    if (x < rows - 1) {
      neighbors.add(game.getField(new Position(x + 1, y)));
    }
    return neighbors;
  }

  public boolean isConnectedWith(Position position) {
    Game game = Game.getInstance();
    Field current = this;
    Field target = game.getField(position);
    HashMap<Position, List<Position>> graph = BFSRunner.buildRoadGraph(game.fields);
    List<Field> currentRoadNeighbors =
        current.getNeighbors().stream().filter(field -> field.getBuilding().isRoad()).toList();
    currentRoadNeighbors.forEach(
        field -> {
          graph.get(field.getPosition()).add(current.getPosition());
        });
    graph.put(
        current.getPosition(), currentRoadNeighbors.stream().map(Field::getPosition).toList());

    List<Field> targetRoadNeighbors =
        target.getNeighbors().stream().filter(field -> field.getBuilding().isRoad()).toList();
    targetRoadNeighbors.forEach(
        field -> {
          graph.get(field.getPosition()).add(target.getPosition());
        });
    graph.put(target.getPosition(), targetRoadNeighbors.stream().map(Field::getPosition).toList());

    return BFSRunner.traverse(graph, current.getPosition(), target.getPosition());
  }

  public boolean isConnectedWithOneOf(List<Position> positions) {
    return positions.stream().anyMatch(this::isConnectedWith);
  }
}
