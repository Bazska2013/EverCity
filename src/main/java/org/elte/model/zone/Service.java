package org.elte.model.zone;

import java.util.ArrayList;
import java.util.List;

import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;

import lombok.Getter;

@Getter
public class Service extends Zone {

  List<Citizen> workers = new ArrayList<>();

  public Service(Position position) {
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
    return FieldTypes.SERVICE;
  }

  @Override
  public int getTax() {
    return tax * capacity;
  }

  @Override
  public int getCitizenNumber() {
    return workers.size();
  }

  @Override
  public List<Citizen> getWorkers() {
    return workers;
  }

  @Override
  public void addCitizen(Citizen citizen) {
    workers.add(citizen);
  }
}
