package org.elte.utils;

import org.elte.model.Field;

import java.util.*;

public class BFSRunner {

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

		public static boolean traverse(
						HashMap<Position, List<Position>> graph,
						Position startingPosition,
						Position targetPosition) {

				HashSet<Position> visited = new HashSet<>();
				LinkedList<Position> queue = new LinkedList<Position>();

				visited.add(startingPosition);
				queue.add(startingPosition);

				while (!queue.isEmpty()) {
						Position currentPosition = queue.poll();
						if (currentPosition == targetPosition) {
								return true;
						}

						graph.get(currentPosition)
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

	public static List<Position> getRoad(
					HashMap<Position, List<Position>> graph,
					Position startingPosition,
					Position targetPosition) {
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

			graph.get(currentPosition).forEach(position -> {
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
