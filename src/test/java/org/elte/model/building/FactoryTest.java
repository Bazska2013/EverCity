package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FactoryTest {

  private Factory factory;

  @Before
  public void setUp() {
    Position position = new Position(0, 0);
    factory = new Factory(position);
  }

  @Test
  public void testIsConnected() {
    assertTrue(factory.isConnected());
  }

  @Test
  public void testUpgradeable() {
    assertTrue(factory.upgradeable());
  }

  @Test
  public void testIsEmpty() {
    assertFalse(factory.isEmpty());
  }

  @Test
  public void testGetTax() {
    int expectedTax = 50;
    int actualTax = factory.getTax();
    assertEquals(expectedTax, actualTax);
  }

  @Test
  public void testBuildingType() {
    BuildingTypes expectedBuildingType = BuildingTypes.FACTORY;
    BuildingTypes actualBuildingType = factory.getBuildingType();
    assertEquals(expectedBuildingType, actualBuildingType);
  }
}
