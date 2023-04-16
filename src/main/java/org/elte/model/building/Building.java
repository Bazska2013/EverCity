package org.elte.model.building;

import lombok.Getter;
import lombok.Setter;
import org.elte.model.enums.BuildingTypes;

import java.awt.*;

@Getter
@Setter
public abstract class Building {
		int price;
		int yearlyFee;
		int socialImpact;
		int impactRange;
		int width;
		int height;
		int chanceToFire;
		boolean burning;
		BuildingTypes buildingType;
		Image imageResource;

		public abstract boolean isPlaceable();

		public abstract boolean isDeletable();

		public abstract boolean isConnected();

		public abstract boolean upgradeable();

		public abstract boolean isBurning();

		public abstract boolean isEmpty();

		public boolean isRoad() {
				return false;
		}

	public abstract void disableDeletable();
	public abstract void removeDeletable();
}
