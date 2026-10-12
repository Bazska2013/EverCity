package org.elte.model.zone;

import java.io.Serializable;

import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;

import lombok.NoArgsConstructor;

/**
 * A Road osztály egy utat reprezentál a játékban. Ez az osztály a Zone osztályból származik, és
 * implementálja az absztrakt metódusokat.
 */
@NoArgsConstructor
public class Road extends Zone implements Serializable {
  /**
   * A Road osztály konstruktora.
   *
   * @param position az út pozíciója
   */
  public Road(Position position) {
    super(position);
    price = 5;
    capacity = 4;
    tax = 5;
    fieldType = FieldTypes.ROAD;
  }
  /**
   * Ellenőrzi, hogy az út törölhető-e.
   *
   * @return true, ha az út törölhető, egyébként false
   */
  @Override
  public boolean isDeletable() {
    return false;
  }
  /**
   * Ellenőrzi, hogy a zóna üres-e.
   *
   * @return true, ha a zóna üres, egyébként false
   */
  @Override
  public boolean isEmpty() {
    return false;
  }
  /**
   * Visszaadja, hogy a zónán lakók mennyi adót kötelesek fizetnek.
   *
   * @return a zónára vonatkozó adó
   */
  @Override
  public int getTax() {
    return 0;
  }
  /**
   * Hozzáad egy polgárt a zónához.
   *
   * @param citizen a hozzáadandó polgár
   */
  @Override
  public void addCitizen(Citizen citizen) {}

  @Override
  public void changeHappiness(int changes) {}
}
