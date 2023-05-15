package org.elte.model.building;

import java.io.Serializable;

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
  /** Visszaadja, hogy az épület ég-e. */
  public boolean isBurning() {
    return false;
  }
  /**
   * Megadja, hogy van-e épület a zónán.
   *
   * @return true, ha nincs épület, különben false.
   */
  public boolean isEmpty() {
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
