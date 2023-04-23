package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;
import org.elte.view.MainFrame;

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
				payment();
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

		public int getTax(){return yearlyFee;}

		public void payment(){
			MainFrame.SetBalance(MainFrame.GetBalance()-price);
		}
}
