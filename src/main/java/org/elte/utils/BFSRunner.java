package org.elte.utils;

import java.util.*;

import org.elte.model.Field;

/** A BFSRunner osztály tartalmazza a BFS algoritmus végrehajtásához szükséges metódusokat. */
public class BFSRunner {
  /**
   * Felépít egy útgráfot a mezőkből a megadott kétdimenziós tömb alapján.
   *
   * @param fields a mezők kétdimenziós tömbje
   * @return a felépített útgráf, amely a mezők pozícióit és az azokhoz tartozó szomszédos
   *     pozíciókat tartalmazza
   */
  public static HashMap<Position, List<Position>> buildRoadGraph(Field[][] fields) {
    HashMap<Position, List<Position>> graph = new HashMap<>();
    int rows = fields.length;
    int cols = fields[0].length;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (fields[i][j].getBuilding().isRoad()) {
          List<Position> neighbors = new ArrayList<>();
          List<Field> allNeighbors = fields[i][j].getNeighbors();
          for (Field neighbor : allNeighbors) {
            Position neighborPos = neighbor.getPosition();
            if (fields[neighborPos.y()][neighborPos.x()].getBuilding().isRoad()) {
              neighbors.add(neighborPos);
            }
          }
          graph.put(new Position(j, i), neighbors);
        }
      }
    }
    return graph;
  }
  /**
   * Elvégzi a szélességi bejárást a megadott útgráfban a kezdőpozíciótól a célpontig.
   *
   * @param graph az útgráf, amely a mezők pozícióit és az azokhoz tartozó szomszédos pozíciókat
   *     tartalmazza
   * @param startingPosition a kezdőpozíció
   * @param targetPosition a célpont pozíciója
   * @return true, ha a célpont elérhető a kezdőpozícióból, false egyébként
   */
  public static boolean traverse(
      HashMap<Position, List<Position>> graph, Position startingPosition, Position targetPosition) {

    HashSet<Position> visited = new HashSet<>();
    LinkedList<Position> queue = new LinkedList<Position>();

    visited.add(startingPosition);
    queue.add(startingPosition);

    while (!queue.isEmpty()) {
      Position currentPosition = queue.poll();
      if (currentPosition == targetPosition) {
        return true;
      }

      graph
          .get(currentPosition)
          .forEach(
              position -> {
                if (!visited.contains(position)) {
                  visited.add(position);
                  queue.add(position);
                }
              });
    }
    return false;
  }
  /**
   * Visszaadja az útvonalat az útgráfban a kezdőpozíciótól a célpontig.
   *
   * @param graph az útgráf, amely a mezők pozícióit és az azokhoz tartozó szomszédos pozíciókat
   *     tartalmazza
   * @param startingPosition a kezdőpozíció
   * @param targetPosition a célpont pozíciója
   * @return az útvonal, ha a célpont elérhető a kezdőpozícióból, null egyébként
   */
  public static List<Position> getRoad(
      HashMap<Position, List<Position>> graph, Position startingPosition, Position targetPosition) {
    HashSet<Position> visited = new HashSet<>();
    LinkedList<Position> queue = new LinkedList<>();
    HashMap<Position, Position> parentMap = new HashMap<>();

    visited.add(startingPosition);
    queue.add(startingPosition);
    parentMap.put(startingPosition, null);

    while (!queue.isEmpty()) {
      Position currentPosition = queue.poll();
      if (currentPosition == targetPosition) {
        // backtrack to construct path
        List<Position> path = new ArrayList<>();
        Position node = targetPosition;
        while (node != null) {
          path.add(node);
          node = parentMap.get(node);
        }
        Collections.reverse(path);
        return path;
      }

      graph
          .get(currentPosition)
          .forEach(
              position -> {
                if (!visited.contains(position)) {
                  visited.add(position);
                  queue.add(position);
                  parentMap.put(position, currentPosition);
                }
              });
    }
    return null;
  }
}
