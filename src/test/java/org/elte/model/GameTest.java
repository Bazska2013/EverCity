package org.elte.model;

import org.elte.model.building.House;
import org.elte.model.building.Road;
import org.elte.model.zone.Resident;
import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
    Resident resident = new Resident(1, 1, 1, 1, 1, new ArrayList<>());
    game.fillFields();
    game.getField(new Position(0, 1)).setZone(resident);
    game.getField(new Position(1, 0)).setZone(resident);
    game.getField(new Position(1, 2)).setZone(resident);
    game.getField(new Position(2, 1)).setZone(resident);
    game.getField(new Position(1, 1)).setBuilding(new Road(new Position(1,1)));
    assertEquals(game.findNewCitizenHousePosition().get(), new Position(1, 2));
    game.getField(new Position(1, 2)).setBuilding(new House());
    assertEquals(game.findNewCitizenHousePosition().get(), new Position(1, 0));
    game.getField(new Position(1, 0)).setBuilding(new House());
    assertEquals(game.findNewCitizenHousePosition().get(), new Position(2, 1));
    game.getField(new Position(2, 1)).setBuilding(new House());
    assertEquals(game.findNewCitizenHousePosition().get(), new Position(0, 1));
  }
}
