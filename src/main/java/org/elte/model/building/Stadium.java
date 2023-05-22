package org.elte.model.building;

import java.io.Serializable;

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
    return burning;
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
}
