package org.elte.model.zone;

import java.io.Serializable;
import java.util.List;

import org.elte.model.Game;
import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Az absztrakt Zone osztály reprezentál egy zónát a játékban. */
@Getter
@Setter
@ToString
public abstract class Zone implements Serializable {
  /** A zóna pozíciója. */
  Position position;

  FieldTypes fieldType;

  public Zone() {
    fieldType = FieldTypes.EMPTY;
  }

  /**
   * Zone osztály konstruktora.
   *
   * @param position a zóna pozíciója
   */
  public Zone(Position position) {
    this.position = position;
    fieldType = FieldTypes.EMPTY;
  }
  /** Az ár, amelyet fizetni kell a zóna létrehozásához. */
  protected int price = 10;
  /** A zóna kapacitása. */
  protected int capacity = 4;
  /** A zóna elégedettsége. */
  protected int hapiness;
  /** Az adó, amelyet a zónán lakók fizetnek. */
  protected int tax;
  /** A zóna fejlesztési szintje. */
  protected int level = 1;
  /**
   * Absztrakt metódus, amely ellenőrzi, hogy a zóna törölhető-e.
   *
   * @return true, ha a zóna törölhető, egyébként false
   */
  public abstract boolean isDeletable();
  /**
   * Ellenőrzi, hogy a zónára helyezhető-e épület.
   *
   * @return true, ha a zónára helyezhető épület, egyébként false
   */
  public boolean isPlaceable() {
    Game game = Game.getInstance();
    return game.getField(this.position).getZone().isEmpty();
  }
  ;
  /** Létrehozza a zónát, ha lehelyezhető. */
  public void create(String s) {
    if (isPlaceable()) {
      Game game = Game.getInstance();
      game.getField(this.position).setZone(this);
      game.setBalance(game.getBalance() - price);
      System.out.println(s);
      game.getAllCosts().add(s + "," + price);
    }
  }
  ;
  /**
   * Absztrakt metódus, amely ellenőrzi, hogy a zóna üres-e.
   *
   * @return true, ha a zóna üres, egyébként false
   */
  public abstract boolean isEmpty();

  /**
   * Absztrakt metódus, amely visszaadja a zóna típusát.
   *
   * @return a zóna típusa
   */
  public FieldTypes getZoneType() {
    return fieldType;
  }
  /**
   * Absztrakt metódus, amely visszaadja, hogy a zónán lakók mennyi adót kötelesek fizetnek.
   *
   * @return a zónára vonatkozó adó
   */
  public abstract int getTax();
  /**
   * Visszaadja a zónán lakó/dolgozó polgárok számát.
   *
   * @return a zónán lakó/dolgozó polgárok száma
   */
  public int getCitizenNumber() {
    return 0;
  }
  /**
   * Visszaadja a zónán dolgozó polgárokat.
   *
   * @return a zónán dolgozó polgárok listája
   */
  public List<Citizen> getWorkers() {
    return null;
  }
  /** A zóna fejlesztése. */
  public void upgrade() {}
  /**
   * Absztrakt metódus, amely hozzáad egy polgárt a zónához.
   *
   * @param citizen a hozzáadandó polgár
   */
  public abstract void addCitizen(Citizen citizen);
  /**
   * Egy dolgozó eltávolítása a zónáról.
   *
   * @param c az eltávolítandó dolgozó
   */
  public void removeWorker(Citizen c) {}
  /**
   * Beállítja a zónán dolgozókat.
   *
   * @param citizens a zónán dolgozók listája
   */
  public void setWorkers(List<Citizen> citizens) {}

    public abstract int getTax();
    public abstract void payment();

}
