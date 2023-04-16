package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;

public class Empty extends Building {

		public Empty() {
				buildingType = BuildingTypes.EMPTY;
				price = 0;
				yearlyFee = 0;
				socialImpact = 0;
				impactRange = 0;
				width = 1;
				height = 1;
				chanceToFire = 0;
				burning = false;
				imageResource = ImageResource.GRASS;
		}

		public boolean isPlaceable() {
				return true;
		}

		public boolean isDeletable() {
				return false;
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
				return true;
		}
	public void disableDeletable(){	}
	public void removeDeletable(){ }
}
