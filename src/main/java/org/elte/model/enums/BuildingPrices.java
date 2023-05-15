package org.elte.model.enums;

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

  private int priceOfBuilding;

  BuildingPrices(int price) {
    priceOfBuilding = price;
  }

  public int getPriceOfBuilding() {
    return priceOfBuilding;
  }
}
