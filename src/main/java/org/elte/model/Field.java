package org.elte.model;

import lombok.Getter;
import lombok.Setter;
import org.elte.model.building.Building;
import org.elte.model.building.Empty;
import org.elte.model.zone.Zone;
import org.elte.utils.Position;

@Getter
@Setter
public class Field {
		Building building;
		private Zone zone;
		private int height = 1;
		private int width = 1;
		int x;
		int y;

		public Field(int x, int y) {
				zone = new org.elte.model.zone.Empty(1000);
				building = new Empty(new Position(x, y));
				this.x = x;
				this.y = y;
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
}
