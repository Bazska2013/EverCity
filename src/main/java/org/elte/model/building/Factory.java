package org.elte.model.building;

import java.io.Serializable;

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

  public boolean isConnected() {
    return true;
  }
  /**
   * Megadja, hogy az épület fejleszthető-e.
   *
   * @return true, ha az épület fejleszthető, különben false.
   */
  public boolean upgradeable() {
    return true;
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
