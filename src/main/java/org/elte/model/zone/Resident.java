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
public class Resident extends Zone {

  protected List<Citizen> citizens = new ArrayList<>();

  public Resident(Position position) {
    super(position);
    this.capacity = 4;

    //    if (MainFrame.startingCitizensToAdd > 0) {
    //      for (int i = 0; i < Math.min(MainFrame.startingCitizensToAdd, capacity); i++) {
    //        citizens.add(new Citizen(this, null, Math.floorMod(new Random().nextInt(), 43) + 18));
    //      }
    //      MainFrame.startingCitizensToAdd -= citizens.size();
    //      MainFrame.getInstance().repaintCitizenNumber();
    //    }
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
    return FieldTypes.RESIDENT;
  }

  @Override
  public int getTax() {
    return tax * capacity;
  }

  @Override
  public int getCitizenNumber() {
    if (citizens != null) {
      return citizens.size();
    }
    return 0;
  }

  public void setCitizens(List<Citizen> citizens) {
    this.citizens = citizens;
  }

  public void upgrade() {
    Game game = Game.getInstance();
    if (this.level == 1) {
      game.pay(100);
      this.level++;
      this.capacity *= 2;
      game.getField(position).getBuilding().setBuildingType(BuildingTypes.HOUSE_2);
    } else if (this.level == 2) {
      game.pay(200);
      this.level++;
      this.capacity *= 2;
      game.getField(position).getBuilding().setBuildingType(BuildingTypes.HOUSE_3);
    }
  }

  public void addCitizen(Citizen citizen) {
    citizens.add(citizen);
  }
}
