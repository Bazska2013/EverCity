package org.elte.model.zone;

import org.elte.view.MainFrame;

import java.util.List;

import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.utils.Position;

    public Service(int capacity, int hapiness, int tax, int price, int level) {
        this.capacity = capacity;
        this.hapiness = hapiness;
        this.tax = tax;
        this.level = level;
        this.price = price;
        payment();
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
   * A zóna fejlesztése. 2-es szint: 200$-ba kerül és megduplázza a kapacitást. 3-as szint: 400$-ba
   * kerül és mégegyszer a kétszeresére növeli a kapacitást.
   */
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
    @Override
    public int getTax() {
        return tax*capacity;
    }
    @Override
    public void payment(){
        MainFrame.SetBalance(MainFrame.GetBalance()-price);
    }

}
