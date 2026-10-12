package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;
import org.elte.view.MainFrame;

public class Forest extends Building {

		int size;

		public Forest() {
				buildingType = BuildingTypes.FOREST;
				price = 10;
				yearlyFee = 5;
				socialImpact = 10;
				impactRange = 5;
				width = 1;
				height = 1;
				chanceToFire = 1;
				burning = false;
				this.size = 1;
				imageResource = ImageResource.FOREST;
				payment();
		}

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

/**
 * A Forest osztály az erdőket reprezentálja a játékban. Ez az osztály a Building osztályból
 * származik.
 */
public class Forest extends Building implements Serializable {
  /** Az erdő mérete. */
  int size;
  /**
   * A Forest osztály konstruktora.
   *
   * @param position az épület pozíciója
   */
  public Forest(Position position) {
    this.position = position;
    buildingType = BuildingTypes.FOREST1;
    price = 10;
    yearlyFee = 5;
    socialImpact = 10;
    impactRange = 3;
    width = 1;
    height = 1;
    chanceToFire = 0.0001;
    chanceToCollapse = 0;
    burning = false;
    this.size = 1;
  }

  public boolean isConnected() {
    return false;
  }
  /**
   * Megadja, hogy az épület fejleszthető-e.
   *
   * @return true, ha az épület fejleszthető, különben false.
   */
  public boolean upgradeable() {
    return false;
  }
  /**
   * Megadja, hogy van-e épület a zónán.
   *
   * @return true, ha nincs épület, különben false.
   */
  public boolean isEmpty() {
    return false;
  }
  /**
   * Visszaadja, hogy a épület éves fenttartási díjját.
   *
   * @return az épületre vonatkozó éves fenttartási díj
   */
  public int getTax() {
    return yearlyFee;
  }
  /**
   * Visszaadja, hogy mekkora az erdő mérete.
   *
   * @return az erdő mérete
   */
  public int getSize() {
    return size;
  }

		public boolean isEmpty() {
				return false;
		}

		public int getTax(){return yearlyFee;}

		public void payment(){
			MainFrame.SetBalance(MainFrame.GetBalance()-price);
		}
}
