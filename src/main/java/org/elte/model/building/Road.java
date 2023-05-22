package org.elte.model.building;

import java.io.Serializable;

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

  public boolean isConnected() {
    return true;
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
   * Megadja, hogy az épület ég-e.
   *
   * @return true, ha az épület ég, különben false.
   */
  public boolean isBurning() {
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
   * Megadja, hogy az épület út-e.
   *
   * @return true, ha az épület út, különben false.
   */
  @Override
  public boolean isRoad() {
    return true;
  }
  /**
   * Visszaadja, hogy a épület éves fenttartási díjját.
   *
   * @return az épületre vonatkozó éves fenttartási díj
   */
  public int getTax() {
    return yearlyFee;
  }
}
