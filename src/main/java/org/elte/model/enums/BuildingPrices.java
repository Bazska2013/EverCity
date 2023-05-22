package org.elte.model.enums;
/**
 * Enum osztály, amely az épületek árait tartalmazza. Az enum értékei az egyes épületekhez rendelt
 * árakat reprezentálják.
 */
public enum BuildingPrices {
  EMPTY(0),
  ROAD(20),
  POLICE(30),
  FACTORY(10),
  SERVICE_BUILDING(0),
  FIRE_STATION(30),
  STADIUM(100),
  FOREST(10),
  HOUSE_1(0),
  HOUSE_2(0),
  HOUSE_3(0);
  /** Az épület ára. */
  private int priceOfBuilding;
  /**
   * Az BuildingPrices enum konstruktora.
   *
   * @param price az épület ára
   */
  BuildingPrices(int price) {
    priceOfBuilding = price;
  }
  /**
   * Visszaadja az épület árát.
   *
   * @return az épület ára
   */
  public int getPriceOfBuilding() {
    return priceOfBuilding;
  }
}
