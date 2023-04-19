package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;

public class Factory extends Building {

		public Factory() {
				buildingType = BuildingTypes.FACTORY;
				price = 10;
				yearlyFee = 5;
				socialImpact = 10;
				impactRange = 10;
				width = 2;
				height = 2;
				chanceToFire = 2;
				burning = false;
				imageResource = ImageResource.FACTORY;
		}

		public boolean isPlaceable() {
				return true;
		}

		public boolean isDeletable() {
				return true;
		}

		public boolean isConnected() {
				return true;
		}

		public boolean upgradeable() {
				return true;
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
