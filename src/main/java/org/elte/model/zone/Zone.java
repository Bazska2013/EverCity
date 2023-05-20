package org.elte.model.zone;

import java.util.List;

import org.elte.model.Game;
import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Zone {
  Position position;

  public Zone(Position position) {
    this.position = position;
  }

  protected int price = 10;
  protected int capacity = 4;
  protected int hapiness;
  protected int tax;
  protected int level = 1;

  public abstract boolean isDeletable();

  public boolean isPlaceable() {
    Game game = Game.getInstance();
    return game.getField(this.position).getZone().isEmpty();
  }
  ;

  public void create(String s) {
    if (isPlaceable()) {
      Game game = Game.getInstance();
      game.getField(this.position).setZone(this);
      game.setBalance(game.getBalance() - price);
      game.getAllCosts().add(s + " díja: " + price + "$");
    }
  }
  ;

  public abstract boolean isEmpty();

  public abstract FieldTypes getZoneType();

  public abstract int getTax();

  public int getCitizenNumber() {
    return 0;
  }

  public List<Citizen> getWorkers() {
    return null;
  }

  public void upgrade() {}

  public abstract void addCitizen(Citizen citizen);

  public void removeWorker(Citizen c) {}

  public void setWorkers(List<Citizen> citizens) {}
}
