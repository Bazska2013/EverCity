package org.elte.model.building;

import org.elte.model.Firetruck;
import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

import lombok.Getter;
import lombok.Setter;

/**
 * A FireStation osztály a tűzoltóságot reprezentálja a játékban. Ez az osztály a Building
 * osztályból származik.
 */
@Getter
@Setter
public class FireStation extends Building {
  /** A tűzoltóállomás tűzoltóautója. */
  Firetruck firetruck;
  /**
   * A FireStation osztály konstruktora.
   *
   * @param position az épület pozíciója
   */
  public FireStation(Position position) {
    this.position = position;
    buildingType = BuildingTypes.FIRE_STATION;
    price = 30;
    yearlyFee = 10;
    socialImpact = 5;
    impactRange = 30;
    width = 1;
    height = 1;
    chanceToFire = 0;
    chanceToCollapse = 0.005;
    burning = false;
  }
  /**
   * Megadja, hogy az épület elhelyezhető-e.
   *
   * @return true, ha az épület elhelyezhető, különben false.
   */
  public boolean isPlaceable() {
    return hasEnoughSpace() && hasRoadAtLeastOneSide();
  }

  @Override
  public boolean isConnected() {
    return false;
  }
  /**
   * Megadja, hogy az épület fejleszthető-e.
   *
   * @return true, ha az épület fejleszthető, különben false.
   */
  public boolean upgradeable() {
    return true;
  }
  /** Visszaadja, hogy az épület ég-e. */
  public boolean isBurning() {
    return false;
  }
  /**
   * Megadja, hogy az épület tűzoltóállomás-e.
   *
   * @return true, ha az épület tűzoltóállomas, különben false.
   */
  @Override
  public boolean isFireStation() {
    return true;
  }
  /**
   * Megadja, hogy van-e épület a zónán.
   *
   * @return true, ha nincs épület, különben false.
   */
  @Override
  public boolean isEmpty() {
    return false;
  }
  /** Törli az épületet. */
  @Override
  public void delete() {
    super.delete();
    Game.getInstance().getFiretrucks().remove(firetruck);
    // List<FiretruckView> oldFiretruckViews = GamePanel.gamePanel.getFiretruckViews();
    // FiretruckView oldFiretruckView = oldFiretruckViews.stream().filter(item ->
    // item.getFiretruck().equals(firetruck)).findFirst().get();
    // GamePanel.gamePanel.remove(oldFiretruckView);
    // GamePanel.gamePanel.setFiretruckViews(oldFiretruckViews.stream().filter(item ->
    // !item.getFiretruck().equals(firetruck)).collect(Collectors.toList()));
    // System.out.println(Game.getInstance().getFiretrucks());
  }
  /** Létrehozza az épületet, ha lehelyezhető. */
  @Override
  public void create(String s) {
    if (this.isPlaceable()) {
      super.create("fire_station");
      firetruck = new Firetruck(this);
      Game.getInstance().getFiretrucks().add(firetruck);
      // FiretruckView firetruckView = new FiretruckView(firetruck);
      // GamePanel.gamePanel.add(firetruckView);
      // GamePanel.gamePanel.getFiretruckViews().add(firetruckView);
    }
  }
  /**
   * Visszaadja, hogy a épület éves fenttartási díjját.
   *
   * @return az épületre vonatkozó éves fenttartási díj
   */
  public int getTax() {
    return yearlyFee;
  }
}
