package org.elte.model.building;

import lombok.Getter;
import lombok.Setter;
import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

public class Forest extends Building {

  int size;

  public Forest(Position position) {
    this.position = position;
    buildingType = BuildingTypes.FOREST;
    price = 10;
    yearlyFee = 5;
    socialImpact = 10;
    impactRange = 3;
    width = 1;
    height = 1;
    chanceToFire = 0.001;
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
  }
}
