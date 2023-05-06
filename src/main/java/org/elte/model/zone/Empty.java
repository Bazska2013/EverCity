package org.elte.model.zone;

import org.elte.model.enums.FieldTypes;
import org.elte.utils.Position;

public class Empty extends Zone {

  public Empty(Position position) {
    super(position);
  }

  @Override
  public boolean isDeletable() {
    return true;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public int getTax() {
    return tax * capacity;
  }

  @Override
  public FieldTypes getZoneType() {
    return FieldTypes.EMPTY;
  }

}
