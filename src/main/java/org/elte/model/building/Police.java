package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;

public class Police extends Building {
		public Police() {
				buildingType = BuildingTypes.POLICE;
				price = 30;
				yearlyFee = 10;
				socialImpact = 5;
				impactRange = 30;
				width = 1;
				height = 1;
				chanceToFire = 1;
				burning = false;
				// TODO: replace null imageResource with valid image
				imageResource = null;
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

		public int getTax(){return yearlyFee;}
}
