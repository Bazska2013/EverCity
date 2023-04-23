package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;
import org.elte.view.MainFrame;

public class Stadium extends Building {

		public Stadium() {
				buildingType = BuildingTypes.STADIUM;
				price = 100;
				yearlyFee = 20;
				socialImpact = 20;
				impactRange = 10;
				width = 2;
				height = 2;
				chanceToFire = 1;
				burning = false;
				imageResource = ImageResource.STADIUM;
				payment();
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
				return false;
		}

		public boolean isBurning() {
				return false;
		}

		public boolean isEmpty() {
				return false;
		}

		public int getTax(){return yearlyFee;}

		public void payment(){
			MainFrame.SetBalance(MainFrame.GetBalance()-price);
		}
}
