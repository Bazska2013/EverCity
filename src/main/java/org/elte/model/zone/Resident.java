package org.elte.model.zone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;
import org.elte.view.MainFrame;

import lombok.Getter;

@Getter
public class Resident extends Zone {

  protected List<Citizen> citizens = new ArrayList<>();

  public Resident(Position position) {
    super(position);

    if (MainFrame.startingCitizensToAdd > 0) {
      for (int i = 0; i < Math.min(MainFrame.startingCitizensToAdd, capacity); i++) {
        citizens.add(new Citizen(this, null, Math.floorMod(new Random().nextInt(), 43) + 18));
      }
      MainFrame.startingCitizensToAdd -= citizens.size();
      MainFrame.getInstance().repaintCitizenNumber();
    }
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
}
