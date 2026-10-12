package org.elte.model.building;

import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmptyBuildingTest {

  private Empty empty;

  @Before
  public void setUp() {
    Position position = new Position(0, 0);
    empty = new Empty(position);
  }

  @Test
  public void testIsConnected() {
    assertFalse(empty.isConnected());
  }

  @Test
  public void testUpgradeable() {
    assertFalse(empty.upgradeable());
  }

  @Test
  public void testIsBurning() {
    assertFalse(empty.isBurning());
  }

  @Test
  public void testIsEmpty() {
    assertTrue(empty.isEmpty());
  }

  @Test
  public void testGetTax() {
    int expectedTax = 0;
    int actualTax = empty.getTax();
    assertEquals(expectedTax, actualTax);
  }
}
