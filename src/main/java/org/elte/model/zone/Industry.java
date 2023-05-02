package org.elte.model.zone;

import java.util.ArrayList;
import java.util.List;

import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;

import lombok.Getter;

@Getter
public class Industry extends Zone {

  List<Citizen> workers = new ArrayList<>();

  public Industry(Position position) {
    super(position);
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
    return FieldTypes.INDUSTRY;
  }

  @Override
  public int getTax() {
    return tax * capacity;
  }
}
