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

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

/**
 * A Empty osztály az üres épületeket reprezentálja a játékban. Ez az osztály a Building osztályból
 * származik.
 */
public class Empty extends Building implements Serializable {
  /**
   * A Empty osztály konstruktora.
   *
   * @param position az épület pozíciója
   */
  public Empty(Position position) {
    this.position = position;
    buildingType = BuildingTypes.EMPTY;
    price = 0;
    yearlyFee = 0;
    socialImpact = 0;
    impactRange = 0;
    width = 1;
    height = 1;
    chanceToFire = 0;
    chanceToCollapse = 0;
    burning = false;
  }
  /**
   * Megadja, hogy az épület elhelyezhető-e.
   *
   * @return true, ha az épület elhelyezhető, különben false.
   */
  @Override
  public boolean isPlaceable() {
    return hasBuildingAtLeastOneSide();
  }

		public boolean isEmpty() {
				return true;
		}

		public int getTax(){return yearlyFee;}

		public void payment(){
			MainFrame.SetBalance(MainFrame.GetBalance()-price);
		}
}
