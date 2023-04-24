package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;
import org.elte.utils.Position;

public class Forest extends Building {

		int size;

		public Forest(Position position) {
				this.position = position;
				buildingType = BuildingTypes.FOREST;
				price = 10;
				yearlyFee = 5;
				socialImpact = 10;
				impactRange = 5;
				width = 1;
				height = 1;
				chanceToFire = 1;
				burning = false;
				this.size = 1;
				imageResource = ImageResource.FOREST;
		}

		public boolean isPlaceable() {

				return hasEnoughSpace();
		}

		public boolean isDeletable() {
				return true;
		}

		public boolean isConnected() {
				return false;
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

		public int getTax(){return yearlyFee;}
}
