package org.elte.model.building;

import java.io.Serializable;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

/**
 * A ServiceBuilding osztály az szolgáltatási épületeket reprezentálja a játékban. Ez az osztály a
 * Building osztályból származik.
 */
public class ServiceBuilding extends Building implements Serializable {
  /**
   * A ServiceBuilding osztály konstruktora.
   *
   * @param position az épület pozíciója
   */
  public ServiceBuilding(Position position) {
    super();
    this.position = position;
    this.buildingType = BuildingTypes.SERVICE_BUILDING;
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
