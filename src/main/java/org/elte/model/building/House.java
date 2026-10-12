package org.elte.model.building;

import org.elte.view.MainFrame;

public class House extends Building {
		@Override
		public boolean isPlaceable() {
				return false;
		}

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

/**
 * A House osztály az erdőket reprezentálja a játékban. Ez az osztály a Building osztályból
 * származik.
 */
public class House extends Building implements Serializable {
  /**
   * A House osztály konstruktora.
   *
   * @param position az épület pozíciója
   */
  public House(Position position) {
    this.position = position;
    buildingType = BuildingTypes.HOUSE_1;
    chanceToFire = 0.0005;
    chanceToCollapse = 0.05;
  }
  /**
   * Megadja, hogy az épület elhelyezhető-e.
   *
   * @return true, ha az épület elhelyezhető, különben false.
   */
  @Override
  public boolean isPlaceable() {
    return hasEnoughSpace();
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

		public void payment(){}

}
