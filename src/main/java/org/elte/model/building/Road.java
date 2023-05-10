package org.elte.model.building;

import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

public class Road extends Building {

  public Road(Position position) {
    this.position = position;
    buildingType = BuildingTypes.ROAD;
    price = 20;
    yearlyFee = 5;
    socialImpact = 0;
    impactRange = 0;
    width = 1;
    height = 1;
  }

  public boolean isConnected() {
    return true;
  }

  public boolean upgradeable() {
    return false;
  }

  public boolean isBurning() {
    return false;
  }

  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean isRoad() {
    return true;
  }

  public int getTax() {
    return yearlyFee;
  }
}
