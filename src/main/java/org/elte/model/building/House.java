package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

public class House extends Building {

  public House(Position position) {
    this.position = position;
    buildingType = BuildingTypes.HOUSE_1;
    chanceToFire = 0.0005;
    chanceToCollapse = 0.05;
  }

  @Override
  public boolean isPlaceable() {
    return hasEnoughSpace();
  }

  @Override
  public boolean isConnected() {
    return false;
  }

  @Override
  public boolean upgradeable() {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  public int getTax() {
    return yearlyFee;
  }
}
