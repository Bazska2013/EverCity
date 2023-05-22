package org.elte.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.elte.model.building.Building;
import org.elte.model.building.Empty;
import org.elte.model.people.Citizen;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Service;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Zone;
import org.elte.utils.BFSRunner;
import org.elte.utils.Position;

import lombok.Getter;
import lombok.Setter;

/** A Field osztály reprezentál egy mezőt a játékban. */
@Getter
@Setter
public class Field {
  Building building;
  private Zone zone;
  private int height = 1;
  private int width = 1;
  Position position;
  List<Building> impactedBy;

  /**
   * Field osztály konstruktora.
   *
   * @param position A mező pozíciója.
   */
  public Field(Position position) {
    zone = new org.elte.model.zone.Empty(position);
    building = new Empty(position);
    this.position = position;
    impactedBy = new ArrayList<>();
  }
  /**
   * Beállítja a mezőhöz tartozó zónát.
   *
   * @param zone A mezőhöz tartozó zóna.
   */
  public void setZone(Zone zone) {
    this.zone = zone;
  }
  /**
   * Visszaadja a mezőhöz tartozó adókat.
   *
   * @return A mezőhöz tartozó adók összege.
   */
  public int getTaxes() {
    return zone.getTax();
  }
  /**
   * Visszaadja a mezőhöz tartozó fenttartási díjakat.
   *
   * @return A mezőhöz tartozó fenttartási díjak összege.
   */
  public int getFees() {
    return building.getTax();
  }

  public int getRetiredPay() {
    if (zone instanceof Resident) {
      return ((Resident) zone).getRetiredPay();
    }
    return 0;
  }
  /**
   * Visszaadja a mező szomszédait.
   *
   * @return A mező szomszédjainak listája.
   */
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
    if (y < rows - 1) {
      neighbors.add(game.getField(new Position(x, y + 1)));
    }
    if (x > 0) {
      neighbors.add(game.getField(new Position(x - 1, y)));
    }
    if (x < cols - 1) {
      neighbors.add(game.getField(new Position(x + 1, y)));
    }
    return neighbors;
  }
  /**
   * Ellenőrzi, hogy a mező kapcsolódik-e a megadott pozícióval.
   *
   * @param position A vizsgált pozíció.
   * @return true, ha a mező kapcsolódik a megadott pozícióhoz, különben false.
   */
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
  /**
   * Visszaadja az utat a mezőhöz a megadott pozícióból.
   *
   * @param position A cél pozíciója.
   * @return Az útvonal a mezőhöz a cél pozícióból.
   */
  public List<Position> getRoadToField(Position position) {
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

    return BFSRunner.getRoad(graph, current.getPosition(), target.getPosition());
  }
  /**
   * Ellenőrzi, hogy a mező kapcsolódik-e az egyik megadott pozícióhoz.
   *
   * @param positions A pozíciók listája.
   * @return true, ha a mező kapcsolódik legalább egy megadott pozícióhoz, különben false.
   */
  public boolean isConnectedWithOneOf(List<Position> positions) {
    return positions.stream().anyMatch(this::isConnectedWith);
  }
  /** Eltávolítja a mezőről a lakosokat vagy a munkavállalókat. */
  public void removeCitizens() {
    if (this.zone instanceof Resident) {
      List<Citizen> citizens = ((Resident) this.zone).getCitizens();
      for (Citizen c : citizens) {
        Zone worksAt = c.getWorksAt();
        if (worksAt != null) {
          List<Citizen> workers = worksAt.getWorkers();
          workers.remove(c);
          c.setWorksAt(null);
          this.zone.setWorkers(workers);
        }
      }
      citizens.removeAll(((Resident) this.zone).getCitizens());
      ((Resident) this.zone).setCitizens(citizens);
    } else if (this.zone instanceof Industry || this.zone instanceof Service) {
      List<Citizen> workers = this.zone.getWorkers();
      if (workers != null) {
        for (Citizen c : workers) {
          c.setWorksAt(null);
        }
      }
      workers.removeAll(this.zone.getWorkers());
      this.zone.setWorkers(workers);
    }
  }

  /*lekéri, impactedBy épületeitől hogy mennyit befolyásolnak*/
  public void affects() {
    int sum = 0;
    for (int i = 0; i < impactedBy.size(); i++) {
      sum += impactedBy.get(i).getSocialImpact();
    }
    zone.changeHappiness(sum);
  }
}
