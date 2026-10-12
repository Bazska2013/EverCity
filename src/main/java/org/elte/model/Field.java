package org.elte.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.elte.model.building.Building;
import org.elte.model.building.Empty;
import org.elte.model.people.Citizen;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Service;
import org.elte.model.zone.Zone;
import org.elte.utils.BFSRunner;
import org.elte.utils.Position;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** A Field osztály reprezentál egy mezőt a játékban. */
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

		public void setZone(Zone zone) {
				this.zone = zone;
		}

		public int getTaxes(){
			return zone.getTax();
		}
		public int getFees() { return building.getTax();}
}
