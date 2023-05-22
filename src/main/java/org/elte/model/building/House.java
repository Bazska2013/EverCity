package org.elte.model.building;

import java.io.Serializable;

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
  public boolean isConnected() {
    return false;
  }
  /**
   * Megadja, hogy az épület fejleszthető-e.
   *
   * @return true, ha az épület fejleszthető, különben false.
   */
  @Override
  public boolean upgradeable() {
    return false;
  }
  /**
   * Megadja, hogy van-e épület a zónán.
   *
   * @return true, ha nincs épület, különben false.
   */
  @Override
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
