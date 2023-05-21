package org.elte.model.zone;

import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;

public class Road extends Zone {

  public Road(Position position) {
    super(position);
    price = 5;
    capacity = 4;
    tax = 5;
  }

  @Override
  public boolean isDeletable() {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public FieldTypes getZoneType() {
    return FieldTypes.ROAD;
  }

  @Override
  public int getTax() {
    return 0;
  }

  @Override
  public void addCitizen(Citizen citizen) {}

  @Override
  public void changeHappiness(int changes) {}
}
