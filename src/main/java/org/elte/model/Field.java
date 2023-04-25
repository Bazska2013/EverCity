package org.elte.model;

import lombok.Getter;
import lombok.Setter;
import org.elte.model.building.Building;
import org.elte.model.building.Empty;
import org.elte.model.zone.Zone;
import org.elte.utils.Position;

import java.util.ArrayList;
import org.elte.utils.Position;

@Getter
@Setter
public class Field {
		Building building;
		private Zone zone;
		private int height = 1;
		private int width = 1;
		Position position;

		public Field(Position position) {
				zone = new org.elte.model.zone.Empty(1000);
				building = new Empty(position);
				this.position = position;
		}

		public void setZone(Zone zone) {
				this.zone = zone;
		}

		public int getTaxes() {
				return zone.getTax();
		}
		public int getFees() { return building.getTax();}

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
}
