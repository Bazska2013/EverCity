package org.elte.model.zone;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZoneTest {

  private Zone zone;
  private Game game;

  @Before
  public void setUp() {
    Position position = new Position(0, 0);
    zone = new Empty(position);
    game = Game.getInstance();
  }

  @Test
  public void testIsDeletable() {
    assertTrue(zone.isDeletable());
  }

  @Test
  public void testIsPlaceable() {
    assertTrue(zone.isPlaceable());

    // Place a zone on the field
    game.getField(zone.getPosition()).setZone(new Resident(zone.getPosition()));

    assertFalse(zone.isPlaceable());
  }

  @Test
  public void testCreate() {
    Position position = new Position(2, 2);
    Zone zoneToCreate = new Empty(position);

    assertTrue(zoneToCreate.isPlaceable());

    zoneToCreate.create("empty zone");

    assertTrue(zoneToCreate.isEmpty());
  }

  @Test
  public void testIsEmpty() {
    Field field = game.getField(new Position(8, 8));
    assertTrue(field.getZone().isEmpty());

    game.getField(field.getPosition()).setZone(new Resident(field.getPosition()));

    assertFalse(field.getZone().isEmpty());
  }

  @Test
  public void testGetZoneType() {
    assertEquals(FieldTypes.EMPTY, zone.getZoneType());
  }

  @Test
  public void testGetTax() {
    assertEquals(0, zone.getTax());
  }

  @Test
  public void testGetCitizenNumber() {
    assertEquals(0, zone.getCitizenNumber());
  }

  @Test
  public void testGetWorkers() {
    assertNull(zone.getWorkers());
  }
}
