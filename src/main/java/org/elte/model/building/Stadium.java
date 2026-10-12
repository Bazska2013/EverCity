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

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

/**
 * A Stadium osztály a stadionokat reprezentálja a játékban. Ez az osztály a Building osztályból
 * származik.
 */
public class Stadium extends Building implements Serializable {
  /**
   * A Stadium osztály konstruktora.
   *
   * @param position az épület pozíciója
   */
  public Stadium(Position position) {
    super();
    this.position = position;
    buildingType = BuildingTypes.STADIUM;
    price = 100;
    yearlyFee = 20;
    socialImpact = 10;
    impactRange = 10;
    width = 2;
    height = 2;
    chanceToFire = 0.0005;
    chanceToCollapse = 0.05;
    burning = false;
  }
  /**
   * Megadja, hogy az épület elhelyezhető-e.
   *
   * @return true, ha az épület elhelyezhető, különben false.
   */
  public boolean isPlaceable() {
    return hasEnoughSpace() && hasRoadAtLeastOneSide();
  }

		public boolean isEmpty() {
				return false;
		}

		public int getTax(){return yearlyFee;}

		public void payment(){
			MainFrame.SetBalance(MainFrame.GetBalance()-price);
		}
}
