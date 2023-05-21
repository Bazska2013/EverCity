package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

public class Forest extends Building {

  int size;

  public Forest(Position position) {
    this.position = position;
    buildingType = BuildingTypes.FOREST1;
    price = 10;
    yearlyFee = 5;
    socialImpact = 10;
    impactRange = 3;
    width = 1;
    height = 1;
    chanceToFire = 0.0001;
    chanceToCollapse = 0;
    burning = false;
    this.size = 1;
  }

  public boolean isConnected() {
    return false;
  }

  public boolean upgradeable() {
    return false;
  }

  public boolean isEmpty() {
    return false;
  }

  public int getTax() {
    return yearlyFee;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = Math.min(10, size);
    if (this.size == 2) {
      this.buildingType = BuildingTypes.FOREST2;
    }
    if (this.size == 3) {
      this.buildingType = BuildingTypes.FOREST3;
    }
    if (this.size == 4) {
      this.buildingType = BuildingTypes.FOREST4;
    }
    if (this.size == 5) {
      this.buildingType = BuildingTypes.FOREST5;
    }
    if (this.size == 6) {
      this.buildingType = BuildingTypes.FOREST6;
    }
    if (this.size == 7) {
      this.buildingType = BuildingTypes.FOREST7;
    }
    if (this.size == 8) {
      this.buildingType = BuildingTypes.FOREST8;
    }
    if (this.size == 9) {
      this.buildingType = BuildingTypes.FOREST9;
    }
    if (this.size == 10) {
      this.buildingType = BuildingTypes.FOREST10;
    }
  }
}
