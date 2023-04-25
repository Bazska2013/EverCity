package org.elte.utils;

import org.elte.model.Field;

import java.util.*;

public class BFSRunner {


  public static HashMap<Position, List<Position>> buildGraph(Field[][] fields){
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
          graph.put(new Position(j,i), neighbors);
        }
      }
    }
    return graph;
  }
  public static void traverse(HashMap<Position, ArrayList<Position>> graph, Position startingPosition) {

    HashSet<Position> visited = new HashSet<>();
    LinkedList<Position> queue
            = new LinkedList<Position>();

    visited.add(startingPosition);
    queue.add(startingPosition);

    while (!queue.isEmpty()){
      Position currentPosition = queue.poll();
      System.out.println(currentPosition + " ");

      graph.get(currentPosition).forEach(position -> {
        if(!visited.contains(position)){
          visited.add(position);
          queue.add(position);
        }
      });
    }
  }
}
