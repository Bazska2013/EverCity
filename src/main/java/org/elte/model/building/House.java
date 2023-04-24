package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;

public class House extends Building {

	public House() {
		buildingType = BuildingTypes.HOUSE_1;
	}

	@Override
		public boolean isPlaceable() {
				return hasEnoughSpace();
		}

		@Override
		public boolean isDeletable() {
				return false;
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

		public int getTax(){return yearlyFee;}

		public void disableDeletable() {}

		public void removeDeletable() {}
}
