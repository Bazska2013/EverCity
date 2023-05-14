package org.elte.model.zone;

import java.util.ArrayList;
import java.util.List;

import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;

import lombok.Getter;

@Getter
public class Service extends Zone {

  List<Citizen> workers = new ArrayList<>();

  public Service(Position position) {
    super(position);
    price = 15;
    capacity = 4;
    hapiness = 50;
    tax = 5;
    level = 1;
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
    if (workers != null) {
      return workers.size();
    }
    return 0;
  }

  @Override
  public List<Citizen> getWorkers() {
    return workers;
  }

  public void removeWorker(Citizen c) {
    if (workers != null) {
      workers.remove(c);
    }
  }

  public void setWorkers(List<Citizen> citizens) {
    workers = citizens;
  }

  public void upgrade() {
    Game game = Game.getInstance();
    if (this.level == 1) {
      game.pay(200);
      this.level++;
      this.capacity *= 2;
      game.getField(position).getBuilding().setBuildingType(BuildingTypes.SERVICE_2);
    } else if (this.level == 2) {
      game.pay(400);
      this.level++;
      this.capacity *= 2;
      game.getField(position).getBuilding().setBuildingType(BuildingTypes.SERVICE_3);
    }
  }

  @Override
  public void addCitizen(Citizen citizen) {
    workers.add(citizen);
  }
}
