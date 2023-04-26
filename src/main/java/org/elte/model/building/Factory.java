package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.ImageResource;
import org.elte.utils.Position;

public class Factory extends Building {

		public Factory(Position position) {
				this.position = position;
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
				return hasEnoughSpace(FieldTypes.INDUSTRY) && hasRoadAtLeastOneSide();
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
