package org.elte.model.building;

import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ForestTest {
  private Forest forest;

  @Before
  public void setUp() {
    Position position = new Position(0, 0);
    forest = new Forest(position);
  }

  @Test
  public void testIsConnected() {
    assertFalse(forest.isConnected());
  }

  @Test
  public void testUpgradeable() {
    assertFalse(forest.upgradeable());
  }

  @Test
  public void testIsEmpty() {
    assertFalse(forest.isEmpty());
  }

  @Test
  public void testGetTax() {
    int expectedTax = 5;
    int actualTax = forest.getTax();
    assertEquals(expectedTax, actualTax);
  }

  @Test
  public void testGetSize() {
    int expectedSize = 1;
    int actualSize = forest.getSize();
    assertEquals(expectedSize, actualSize);
  }

  @Test
  public void testSetSizeWithinLimit() {
    int newSize = 5;
    forest.setSize(newSize);
    assertEquals(newSize, forest.getSize());
  }

  @Test
  public void testSetSizeExceedingLimit() {
    int newSize = 15;
    forest.setSize(newSize);
    int expectedSize = 10;
    int actualSize = forest.getSize();
    assertEquals(expectedSize, actualSize);
  }

  @Test
  public void testBuildingType() {
    BuildingTypes expectedBuildingType = BuildingTypes.FOREST1;
    BuildingTypes actualBuildingType = forest.getBuildingType();
    assertEquals(expectedBuildingType, actualBuildingType);
  }
}
