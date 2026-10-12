package org.elte.model.building;

import java.awt.*;

import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FireStationTest {
  private FireStation fireStation;

  @Before
  public void setUp() {
    Position position = new Position(0, 0);
    Game.getInstance().fields[0][0].setBuilding(new Empty(position));
    Game.getInstance().fields[0][1].setBuilding(new Road(new Position(1, 0)));
    Game.getInstance().fields[0][1].setZone(new org.elte.model.zone.Road(new Position(1, 0)));
    fireStation = new FireStation(position);
  }

  @Test
  public void testIsConnected() {
    assertFalse(fireStation.isConnected());
  }

  @Test
  public void testUpgradeable() {
    assertTrue(fireStation.upgradeable());
  }

  @Test
  public void testIsBurning() {
    assertFalse(fireStation.isBurning());
  }

  @Test
  public void testIsFireStation() {
    assertTrue(fireStation.isFireStation());
  }

  @Test
  public void testIsEmpty() {
    assertFalse(fireStation.isEmpty());
  }

  @Test
  public void testGetTax() {
    int expectedTax = 10;
    int actualTax = fireStation.getTax();
    assertEquals(expectedTax, actualTax);
  }

  @Test
  public void testBuildingType() {
    BuildingTypes expectedBuildingType = BuildingTypes.FIRE_STATION;
    BuildingTypes actualBuildingType = fireStation.getBuildingType();
    assertEquals(expectedBuildingType, actualBuildingType);
  }
}
