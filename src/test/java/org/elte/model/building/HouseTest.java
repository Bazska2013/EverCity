package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HouseTest {
  private House house;

  @Before
  public void setUp() {
    Position position = new Position(0, 0);
    house = new House(position);
  }

  @Test
  public void testIsConnected() {
    assertFalse(house.isConnected());
  }

  @Test
  public void testUpgradeable() {
    assertFalse(house.upgradeable());
  }

  @Test
  public void testIsEmpty() {
    assertFalse(house.isEmpty());
  }

  @Test
  public void testGetTax() {
    int expectedTax = 0;
    int actualTax = house.getTax();
    assertEquals(expectedTax, actualTax);
  }

  @Test
  public void testBuildingType() {
    BuildingTypes expectedBuildingType = BuildingTypes.HOUSE_1;
    BuildingTypes actualBuildingType = house.getBuildingType();
    assertEquals(expectedBuildingType, actualBuildingType);
  }
}
