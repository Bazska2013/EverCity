package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;
import org.elte.view.MainFrame;

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
				payment();
		}

		public boolean isPlaceable() {
				return true;
		}

import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.Position;

/**
 * A Factory osztály a gyár épületeket reprezentálja a játékban. Ez az osztály a Building osztályból
 * származik.
 */
public class Factory extends Building implements Serializable {
  /**
   * A Factory osztály konstruktora.
   *
   * @param position az épület pozíciója
   */
  public Factory(Position position) {
    this.position = position;
    buildingType = BuildingTypes.FACTORY;
    price = 10;
    yearlyFee = 50;
    socialImpact = -5;
    impactRange = 10;
    width = 2;
    height = 2;
    chanceToFire = 0.002;
    chanceToCollapse = 0.02;
    burning = false;
  }
  /**
   * Megadja, hogy az épület elhelyezhető-e.
   *
   * @return true, ha az épület elhelyezhető, különben false.
   */
  public boolean isPlaceable() {
    return hasEnoughSpace(FieldTypes.INDUSTRY) && hasRoadAtLeastOneSide();
  }

		public boolean isEmpty() {
				return false;
		}

		public int getTax(){return yearlyFee;}

		public void payment(){
			MainFrame.SetBalance(MainFrame.GetBalance()-price);
		}
}
