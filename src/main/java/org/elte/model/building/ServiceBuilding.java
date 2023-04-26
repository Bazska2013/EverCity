package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

public class ServiceBuilding extends Building {

		public ServiceBuilding(Position position) {
				super();
				this.position = position;
				this.buildingType = BuildingTypes.SERVICE_BUILDING;
		}

		@Override
		public boolean isConnected() {
				return false;
		}

		@Override
		public boolean upgradeable() {
				return false;
		}

		@Override
		public boolean isBurning() {
				return false;
		}

		@Override
		public boolean isEmpty() {
				return false;
		}

		@Override
		public int getTax() {
				return 0;
		}
}
