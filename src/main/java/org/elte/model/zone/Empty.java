package org.elte.model.zone;

import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;

/**
 * A Empty osztály az üres zónákat reprezentálja a játékban, ahova még nem lett lehelyezve
 * Resident/Service/Industry/Road zóna. Ez az osztály a Zone osztályból származik, és implementálja
 * az absztrakt metódusokat.
 */
public class Empty extends Zone {
  /**
   * A Empty osztály konstruktora.
   *
   * @param position a zóna pozíciója
   */
  public Empty(Position position) {
    super(position);
    price = -6;
  }
  /**
   * Ellenőrzi, hogy a zóna törölhető-e.
   *
   * @return true, ha a zóna törölhető, egyébként false
   */
  @Override
  public boolean isDeletable() {
    return true;
  }
  /**
   * Ellenőrzi, hogy a zóna üres-e.
   *
   * @return true, ha a zóna üres, egyébként false
   */
  @Override
  public boolean isEmpty() {
    return true;
  }
  /**
   * Visszaadja, hogy a zónán lakók mennyi adót kötelesek fizetnek.
   *
   * @return a zónára vonatkozó adó
   */
  @Override
  public int getTax() {
    return tax * capacity;
  }
  /**
   * Visszaadja a zóna típusát.
   *
   * @return a zóna típusa
   */
  @Override
  public FieldTypes getZoneType() {
    return FieldTypes.EMPTY;
  }
  /**
   * Hozzáad egy polgárt a zónához.
   *
   * @param citizen a hozzáadandó polgár
   */
  @Override
  public void addCitizen(Citizen citizen) {}

  public void changeHappiness(int changes) {}
}
