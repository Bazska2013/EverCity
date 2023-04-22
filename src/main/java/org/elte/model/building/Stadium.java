package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

public class Stadium extends Building {

		public Stadium(Position position) {
				super();
				this.position = position;
				buildingType = BuildingTypes.STADIUM;
				price = 100;
				yearlyFee = 20;
				socialImpact = 20;
				impactRange = 10;
				width = 2;
				height = 2;
				chanceToFire = 1;
				burning = false;
		}

		public boolean isPlaceable() {
				return hasEnoughSpace() && hasRoadAtLeastOneSide();
		}

		public boolean isDeletable() {
				return true;
		}

		public boolean isConnected() {
				return true;
		}

		public boolean upgradeable() {
				return false;
		}

		public boolean isBurning() {
				return false;
		}

		public boolean isEmpty() {
				return false;
		}

		public void disableDeletable() {}

		public void removeDeletable() {}
}
