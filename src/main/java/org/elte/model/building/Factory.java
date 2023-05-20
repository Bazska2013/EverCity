package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.Position;

public class Factory extends Building {

  public Factory(Position position) {
    this.position = position;
    buildingType = BuildingTypes.FACTORY;
    price = 10;
    yearlyFee = 50;
    socialImpact = 10;
    impactRange = 10;
    width = 2;
    height = 2;
    chanceToFire = 0.002;
    chanceToCollapse = 0.02;
    burning = false;
  }

  public boolean isPlaceable() {
    return hasEnoughSpace(FieldTypes.INDUSTRY) && hasRoadAtLeastOneSide();
  }

  public boolean isConnected() {
    return true;
  }

  public boolean upgradeable() {
    return true;
  }

  public boolean isEmpty() {
    return false;
  }

  public int getTax() {
    return yearlyFee;
  }
}
