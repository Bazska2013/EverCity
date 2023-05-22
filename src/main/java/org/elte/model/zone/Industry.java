package org.elte.model.zone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A Industry osztály az ipari zónákat reprezentálja a játékban. Ez az osztály a Zone osztályból
 * származik, és implementálja az absztrakt metódusokat.
 */
@Getter
@NoArgsConstructor
public class Industry extends Zone implements Serializable {
  /** A zónában dolgozó polgárok listája. */
  List<Citizen> workers = new ArrayList<>();
  /**
   * A Industry osztály konstruktora.
   *
   * @param position a terület pozíciója
   */
  public Industry(Position position) {
    super(position);
    price = 15;
    capacity = 8;
    hapiness = 50;
    tax = 5;
    level = 1;
    fieldType = FieldTypes.INDUSTRY;
  }
  /**
   * Ellenőrzi, hogy a zóna törölhető-e.
   *
   * @return true, ha a zóna törölhető, egyébként false
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

  @Override
  public int getTax() {
    return tax * capacity;
  }
  /**
   * Visszaadja a zónán dolgozó polgárok számát.
   *
   * @return a zónán dolgozó polgárok száma
   */
  @Override
  public int getCitizenNumber() {
    if (workers != null) {
      return workers.size();
    }
    return 0;
  }
  /**
   * Visszaadja a zónán dolgozó polgárokat.
   *
   * @return a zónán dolgozó polgárok listája
   */
  @Override
  public List<Citizen> getWorkers() {
    return workers;
  }
  /**
   * Egy dolgozó munkájának megszüntetése.
   *
   * @param c az eltávolítandó dolgozó
   */
  public void removeWorker(Citizen c) {
    if (workers != null) {
      workers.remove(c);
    }
  }
  /**
   * A dolgozók beállítása.
   *
   * @param citizens a dolgozók listája
   */
  public void setWorkers(List<Citizen> citizens) {
    workers = citizens;
  }
  /**
   * A zóna fejlesztése. 2-es szint: 300$-ba kerül és megduplázza a kapacitást. 3-as szint: 600$-ba
   * kerül és mégegyszer a kétszeresére növeli a kapacitást.
   */
  public void upgrade() {
    Game game = Game.getInstance();
    if (this.level == 1) {
      game.pay(300);
      this.level++;
      this.capacity *= 2;
      game.getField(position).getBuilding().setBuildingType(BuildingTypes.INDUSTRY_2);
    } else if (this.level == 2) {
      game.pay(600);
      this.level++;
      this.capacity *= 2;
      game.getField(position).getBuilding().setBuildingType(BuildingTypes.INDUSTRY_3);
    }
  }
  /**
   * Hozzáad egy polgárt a zónához.
   *
   * @param citizen a hozzáadandó polgár
   */
  @Override
  public void addCitizen(Citizen citizen) {
    workers.add(citizen);
  }

  @Override
  public void changeHappiness(int changes) {
    int ch = 0;
    if (tax >= 10) {
      ch -= 5;
    } else if (tax <= 5) {
      ch += 5;
    }
    for (Citizen c : workers) {
      ch += c.workHomeDistance();
    }
    Game game = Game.getInstance();
    ch -= game.balanceAffect();
    ch += Game.indServBalance();
    this.hapiness = (50 + (ch + changes));
  }
}
