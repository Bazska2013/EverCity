package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;
import org.elte.view.MainFrame;

public class Road extends Building {

		public Road() {
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
				payment();
		}

		public boolean isPlaceable() {
				return true;
		}

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

/**
 * A Road osztály az utakat reprezentálja a játékban. Ez az osztály a Building osztályból származik.
 */
public class Road extends Building implements Serializable {
  /**
   * A Road osztály konstruktora.
   *
   * @param position az épület pozíciója
   */
  public Road(Position position) {
    this.position = position;
    buildingType = BuildingTypes.ROAD;
    price = 20;
    yearlyFee = 5;
    socialImpact = 0;
    impactRange = 0;
    chanceToFire = 0;
    chanceToCollapse = 0;
    width = 1;
    height = 1;
  }

		@Override
		public boolean isRoad() {
				return true;
		}

		public int getTax(){return yearlyFee;}

		public void payment(){
			MainFrame.SetBalance(MainFrame.GetBalance()-price);
		}
}
