package org.elte.model;

import java.util.List;

import org.elte.model.building.FireStation;
import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FiretruckTest {
  private Firetruck firetruck;
  private FireStation fireStation;

  @Before
  public void setUp() {
    fireStation = new FireStation(new Position(0, 0));
    firetruck = new Firetruck(fireStation);
  }

  @Test
  public void testIsMoving() {
    assertFalse(firetruck.isMoving());

    firetruck.start();

    assertTrue(firetruck.isMoving());
  }

  @Test
  public void testSetTarget() {
    Position target = new Position(2, 2);
    Field currentField = Game.getInstance().getField(firetruck.getPosition());
    List<Position> expectedRoad = currentField.getRoadToField(target);

    firetruck.setTarget(target);

    assertEquals(target, firetruck.getTarget());
    assertEquals(expectedRoad, firetruck.getRoad());
  }

  @Test
  public void testStart() {
    assertFalse(firetruck.isMoving());

    firetruck.start();

    assertTrue(firetruck.isMoving());
  }

  @Test
  public void testEquals() {
    Firetruck sameFiretruck = new Firetruck(fireStation);
    Firetruck differentFiretruck = new Firetruck(new FireStation(new Position(1, 1)));

    assertTrue(firetruck.equals(sameFiretruck));
    assertFalse(firetruck.equals(differentFiretruck));
  }
}
