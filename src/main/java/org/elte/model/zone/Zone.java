package org.elte.model.zone;

import org.elte.model.Game;
import org.elte.model.enums.FieldTypes;
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

  protected int price;
  protected int capacity;
  protected int hapiness;
  protected int tax;
  protected int level;

  public abstract boolean isDeletable();

  public boolean isPlaceable() {
    Game game = Game.getInstance();
    return game.getField(this.position).getZone().isEmpty();
  }
  ;

  public void create() {
    if (isPlaceable()) {
      Game game = Game.getInstance();
      game.getField(this.position).setZone(this);
    }
  }
  ;

  public abstract boolean isEmpty();

  public abstract FieldTypes getZoneType();

  public abstract int getTax();
}
