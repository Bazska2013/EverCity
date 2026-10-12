package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.view.MainFrame;

public class FireStation extends Building {
		public FireStation() {
				buildingType = BuildingTypes.FIRE_STATION;
				price = 30;
				yearlyFee = 10;
				socialImpact = 5;
				impactRange = 30;
				width = 1;
				height = 1;
				chanceToFire = 0;
				burning = false;
				payment();
		}

		public boolean isPlaceable() {
				return true;
		}

import org.elte.model.Firetruck;
import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

import lombok.Getter;
import lombok.Setter;

/**
 * A FireStation osztály a tűzoltóságot reprezentálja a játékban. Ez az osztály a Building
 * osztályból származik.
 */
@Getter
@Setter
public class FireStation extends Building implements Serializable {
  /** A tűzoltóállomás tűzoltóautója. */
  Firetruck firetruck;
  /**
   * A FireStation osztály konstruktora.
   *
   * @param position az épület pozíciója
   */
  public FireStation(Position position) {
    this.position = position;
    buildingType = BuildingTypes.FIRE_STATION;
    price = 30;
    yearlyFee = 10;
    socialImpact = 5;
    impactRange = 30;
    width = 1;
    height = 1;
    chanceToFire = 0;
    chanceToCollapse = 0.005;
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
