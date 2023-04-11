package org.elte.model;

import org.elte.model.building.Police;
import org.elte.model.building.Road;
import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class GameTest {
		Game game;

		@Before
		public void setUp() throws Exception {
				game = Game.getInstance();
		}

		@Test
		public void findNewCitizenHousePositionNoRoad() {
				game.fillFields();
				assertEquals(game.findNewCitizenHousePosition(), Optional.empty());
		}

		@Test
		public void findNewCitizenHousePosition() {
				game.fillFields();
				game.getField(new Position(1, 1)).setBuilding(new Road());
				assertEquals(game.findNewCitizenHousePosition().get(), new Position(1, 2));
				game.getField(new Position(2, 1)).setBuilding(new Police());
				assertEquals(game.findNewCitizenHousePosition().get(), new Position(1, 2));
				game.getField(new Position(0, 1)).setBuilding(new Police());
				assertEquals(game.findNewCitizenHousePosition().get(), new Position(1, 2));
				game.getField(new Position(1, 2)).setBuilding(new Police());
				assertEquals(game.findNewCitizenHousePosition().get(), new Position(1, 0));
		}
}
