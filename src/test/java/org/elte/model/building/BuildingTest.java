package org.elte.model.building;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuildingTest {
  private Building building;
  private Game game;

  @Before
  public void setUp() {
    Position position = new Position(0, 0);
    building = new Empty(position);
    game = Game.getInstance();
    game.fields[0][0].setBuilding(building);
    game.fields[0][0].setZone(new org.elte.model.zone.Empty(position));
  }

  @Test
  public void testHasEnoughSpace() {
    assertTrue(building.hasEnoughSpace(FieldTypes.EMPTY));
  }

  @Test
  public void testHasRoadAtLeastOneSide() {
    Position roadPosition = new Position(0, 1);
    Game.getInstance().fields[1][0].setBuilding(new Empty(roadPosition));
    Game.getInstance().fields[1][0].setZone(new org.elte.model.zone.Empty(roadPosition));
    Game.getInstance().fields[0][1].setBuilding(new Empty(new Position(1, 0)));
    Game.getInstance().fields[0][1].setZone(new org.elte.model.zone.Empty(new Position(1, 0)));
    assertFalse(building.hasRoadAtLeastOneSide());

    Field roadField = game.getField(roadPosition);
    roadField.setBuilding(new Road(roadPosition));

    assertTrue(building.hasRoadAtLeastOneSide());
  }

  @Test
  public void testHasBuildingAtLeastOneSide() {
    assertFalse(building.hasBuildingAtLeastOneSide());

    Position buildingPosition = new Position(1, 0);
    Field buildingField = game.getField(buildingPosition);
    buildingField.setBuilding(new Factory(buildingPosition));

    assertTrue(building.hasBuildingAtLeastOneSide());
  }

  @Test
  public void testCreate() {
    Game.getInstance().fields[5][10].setBuilding(new Empty(new Position(10, 5)));
    Game.getInstance().fields[5][11].setBuilding(new Road(new Position(11, 5)));
    Game.getInstance().fields[5][11].setZone(new org.elte.model.zone.Road(new Position(11, 5)));
    Position position = new Position(10, 5);
    Building buildingToCreate = new Road(position);

    assertTrue(buildingToCreate.isPlaceable());
    buildingToCreate.create("road");

    assertTrue(buildingToCreate.isRoad());
  }

  @Test
  public void testDelete() {
    Position position = new Position(3, 3);
    Building buildingToDelete = new Empty(position);

    // Add a building to be deleted
    Field field = game.getField(position);
    field.setBuilding(new Factory(position));

    buildingToDelete.delete();

    assertTrue(buildingToDelete.isEmpty());

    assertTrue(field.getBuilding().isEmpty());
  }
}
