package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

public class Rubble extends Building {

  public Rubble(Position position) {
    this.position = position;
    buildingType = BuildingTypes.RUBBLE;
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

  @Override
  public int getTax() {
    return 0;
  }
}
