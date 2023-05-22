package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

/**
 * A Rubble osztály a leégett házakat reprezentálja a játékban. Ez az osztály a Building osztályból
 * származik.
 */
public class Rubble extends Building {
  /**
   * A Rubble osztály konstruktora.
   *
   * @param position az épület pozíciója
   */
  public Rubble(Position position) {
    this.position = position;
    buildingType = BuildingTypes.RUBBLE;
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
  @Override
  public int getTax() {
    return 0;
  }
}
