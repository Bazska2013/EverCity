package org.elte.model.zone;

import java.util.ArrayList;
import java.util.List;

import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;

import lombok.Getter;

/**
 * A Resident osztály egy lakó zónát reprezentálja a játékban. Ez az osztály a Zone osztályból
 * származik, és implementálja az absztrakt metódusokat.
 */
@Getter
public class Resident extends Zone {
  /** A zónán élő polgárok listája. */
  protected List<Citizen> citizens = new ArrayList<>();
  /**
   * A Resident osztály konstruktora.
   *
   * @param position a zóna pozíciója
   */
  public Resident(Position position) {
    super(position);
    price = 10;
    capacity = 4;
    hapiness = 50;
    tax = 5;
    level = 1;
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
  /**
   * Visszaadja a zóna típusát.
   *
   * @return a zóna típusa
   */
  @Override
  public FieldTypes getZoneType() {
    return FieldTypes.RESIDENT;
  }

  public int isHereRetired() {
    int j = 0;
    for (int i = 0; i < citizens.size(); i++) {
      if (citizens.get(i).getAge() > 65) {
        j++;
      }
    }
    return j;
  }

  /**
   * Visszaadja, hogy a zónán lakók mennyi adót kötelesek fizetnek.
   *
   * @return a zónára vonatkozó adó
   */
  @Override
  public int getTax() {
    if (isHereRetired() == 0) {
      return tax * capacity;
    }
    return tax * (capacity - isHereRetired());
  }
  /**
   * Visszaadja a zónán lakó polgárok számát.
   *
   * @return a zónán dolgozó polgárok száma
   */
  @Override
  public int getCitizenNumber() {
    if (citizens != null) {
      return citizens.size();
    }
    return 0;
  }
  /**
   * A zónában élő polgárok beállítása.
   *
   * @param citizens a zónán élő polgárok listája
   */
  public void setCitizens(List<Citizen> citizens) {
    this.citizens = citizens;
  }
  /**
   * A zóna fejlesztése. 2-es szint: 100$-ba kerül és megduplázza a kapacitást. 3-as szint: 200$-ba
   * kerül és mégegyszer a kétszeresére növeli a kapacitást.
   */
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
  /**
   * Egy polgár hozzáadása a zónához.
   *
   * @param citizen a hozzáadandó polgár
   */
  public void addCitizen(Citizen citizen) {
    citizens.add(citizen);
  }

  public int getRetiredPay() {
    int pay = 0;
    if (isHereRetired() == 0) {
      return pay;
    }
    for (int i = 0; i < citizens.size(); i++) {
      if (citizens.get(i).getAge() > 65) {
        pay += (tax / 2);
      }
    }
    return pay;
  }

  @Override
  public void changeHappiness(int changes) {
    int ch = 0;
    if (tax >= 10) {
      ch -= 5;
    } else if (tax <= 5) {
      ch += 5;
    }
    for (Citizen c : citizens) {
      ch += c.workHomeDistance();
    }
    Game game = Game.getInstance();
    ch -= game.balanceAffect();
    ch += Game.indServBalance();
    if ((50 + (ch + changes)) >= 100) {
      this.hapiness = 100;
      for (Citizen c : citizens) {
        c.setSatisfaction(100);
      }
    } else if ((50 + (ch + changes)) <= 0) {
      this.hapiness = 0;
      for (Citizen c : citizens) {
        c.setSatisfaction(0);
      }
    } else {
      this.hapiness = (50 + (ch + changes));
      for (Citizen c : citizens) {
        c.setSatisfaction((50 + (ch + changes)) + c.workHomeDistance());
      }
    }
  }
}
