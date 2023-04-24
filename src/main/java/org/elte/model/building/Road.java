package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;
import org.elte.utils.Position;

public class Road extends Building {
		int deletable = 0;

		public Road(Position position) {
			this.position = position;
				buildingType = BuildingTypes.ROAD;
				price = 20;
				yearlyFee = 5;
				socialImpact = 0;
				impactRange = 0;
				width = 1;
				height = 1;
				chanceToFire = 0;
				burning = false;
				imageResource = ImageResource.ROAD;
		}

		public void disableDeletable() {
				this.deletable++;
		}

		public void removeDeletable() {
				this.deletable--;
		}

		public boolean isPlaceable() {
				return hasEnoughSpace();
		}

		public boolean isDeletable() {
				return deletable == 0;
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

		@Override
		public boolean isRoad() {
				return true;
		}

		public int getTax(){return yearlyFee;}
}
