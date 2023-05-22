package org.elte.model;

import java.util.ArrayList;
import java.util.List;

import org.elte.model.building.Building;
import org.elte.model.building.House;
import org.elte.model.building.Road;
import org.elte.model.people.Citizen;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Zone;
import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {
  private Field field;
  private Position position;

  @Before
  public void setUp() {
    position = new Position(0, 0);
    field = new Field(position);
  }

  @Test
  public void testGetTaxes() {
    Zone zone = new Resident(position);
    field.setZone(zone);

    int expectedTax = zone.getTax();

    assertEquals(expectedTax, field.getTaxes());
  }

  @Test
  public void testGetFees() {
    Building building = new House(new Position(0, 0));
    field.building = building;

    int expectedTax = building.getTax();

    assertEquals(expectedTax, field.getFees());
  }

  @Test
  public void testGetNeighbors() {
    Game game = Game.getInstance();
    Position position1 = new Position(0, 1);
    Position position2 = new Position(1, 0);
    Field neighbor1 = new Field(position1);
    Field neighbor2 = new Field(position2);
    game.fields[1][0] = (neighbor1);
    game.fields[0][1] = (neighbor2);

    ArrayList<Field> expectedNeighbors = new ArrayList<>();
    expectedNeighbors.add(neighbor1);
    expectedNeighbors.add(neighbor2);

    assertEquals(expectedNeighbors, field.getNeighbors());
  }

  @Test
  public void testGetRoadToField() {
    Game game = Game.getInstance();
    Position position1 = new Position(0, 1);
    Position position2 = new Position(1, 0);
    Field neighbor1 = new Field(position1);
    Field neighbor2 = new Field(position2);
    neighbor1.building = new Road(position1);
    neighbor2.building = new Road(position2);
    game.fields[1][0] = (neighbor1);
    game.fields[0][1] = (neighbor2);

    List<Position> expectedRoad1 = new ArrayList<>();
    expectedRoad1.add(new Position(0, 0));
    expectedRoad1.add(new Position(0, 1));

    List<Position> expectedRoad2 = new ArrayList<>();
    expectedRoad2.add(new Position(0, 0));
    expectedRoad2.add(new Position(1, 0));

    assertEquals(expectedRoad1, field.getRoadToField(position1));
    assertEquals(expectedRoad2, field.getRoadToField(position2));
  }

  @Test
  public void testRemoveCitizens() {
    Resident residentialZone = new Resident(position);
    Citizen citizen1 = new Citizen(null, null, 0);
    Citizen citizen2 = new Citizen(null, null, 0);
    residentialZone.addCitizen(citizen1);
    residentialZone.addCitizen(citizen2);
    field.setZone(residentialZone);

    assertEquals(2, residentialZone.getCitizens().size());

    field.removeCitizens();

    assertEquals(0, residentialZone.getCitizens().size());
    assertNull(citizen1.getWorksAt());
    assertNull(citizen2.getWorksAt());
  }
}
