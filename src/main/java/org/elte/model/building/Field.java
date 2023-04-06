package org.elte.model.building;

import lombok.Getter;
import lombok.Setter;
import org.elte.model.enums.FieldTypes;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Zone;

@Getter
@Setter
public class Field {
		Building building;
		private Zone zone;
		int x;
		int y;

		public Field(int x, int y) {
				zone = new org.elte.model.zone.Empty(1000);
				building = new Empty();
				this.x = x;
				this.y = y;
		}

		public FieldTypes getZoneType() {
				if (zone instanceof org.elte.model.zone.Empty) return FieldTypes.EMPTY;
				else if (zone instanceof Industry) return FieldTypes.INDUSTRY;
				else if (zone instanceof Resident) return FieldTypes.RESIDENT;
				else return FieldTypes.SERVICE;
		}
}
