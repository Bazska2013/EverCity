package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.view.MainFrame;

public class Police extends Building {
		public Police() {
				buildingType = BuildingTypes.POLICE;
				price = 30;
				yearlyFee = 10;
				socialImpact = 5;
				impactRange = 30;
				width = 1;
				height = 1;
				chanceToFire = 1;
				burning = false;
				// TODO: replace null imageResource with valid image
				imageResource = null;
				payment();
		}

		public boolean isPlaceable() {
				return true;
		}

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

/**
 * A Police osztály a rendőrség épületét reprezentálja a játékban. Ez az osztály a Building
 * osztályból származik.
 */
public class Police extends Building implements Serializable {
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

		public boolean isEmpty() {
				return false;
		}

		public int getTax(){return yearlyFee;}

		public void payment(){
			MainFrame.SetBalance(MainFrame.GetBalance()-price);
		}

}
