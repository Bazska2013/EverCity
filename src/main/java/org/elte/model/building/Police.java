package org.elte.model.building;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

/**
 * A Police osztály a rendőrség épületét reprezentálja a játékban. Ez az osztály a Building
 * osztályból származik.
 */
public class Police extends Building {
  /**
   * A Police osztály konstruktora.
   *
   * @param position az épület pozíciója
   */
  public Police(Position position) {
    this.position = position;
    buildingType = BuildingTypes.POLICE;
    price = 30;
    yearlyFee = 10;
    socialImpact = 10;
    impactRange = 30;
    width = 1;
    height = 1;
    chanceToFire = 0.0005;
    chanceToCollapse = 0.05;
    burning = false;
  }
  /**
   * Megadja, hogy az épület elhelyezhető-e.
   *
   * @return true, ha az épület elhelyezhető, különben false.
   */
  public boolean isPlaceable() {
    Game game = Game.getInstance();
    Field currentField = game.getField(position);

    return hasEnoughSpace() && currentField.getZone().isEmpty() && hasRoadAtLeastOneSide();
  }
  /** ??? */
  public boolean isConnected() {
    return true;
  }
  /**
   * Megadja, hogy az épület fejleszthető-e.
   *
   * @return true, ha az épület fejleszthető, különben false.
   */
  public boolean upgradeable() {
    return true;
  }
  /**
   * Megadja, hogy van-e épület a zónán.
   *
   * @return true, ha nincs épület, különben false.
   */
  public boolean isEmpty() {
    return false;
  }
  /**
   * Visszaadja, hogy a épület éves fenttartási díjját.
   *
   * @return az épületre vonatkozó éves fenttartási díj
   */
  public int getTax() {
    return yearlyFee;
  }
  /**
   * Megadja, hogy az épület képes-e kigyulladni.
   *
   * @return true, ha az épület képes, különben false.
   */
  @Override
  public boolean canBurn() {
    return true;
  }
}
