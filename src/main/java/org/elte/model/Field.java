package org.elte.model;

import lombok.Getter;
import org.elte.model.building.Building;
import org.elte.model.building.Empty;

@Getter
public class Field {
		Building buildings;
		// Zone zone;
		int x;
		int y;

		public Field(int x, int y) {
				// zone = üres zone;
				buildings = new Empty(); // Alapból üres building
				this.x = x;
				this.y = y;
		}
}
