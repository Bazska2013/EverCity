package org.elte.view.game.gamepanel;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.building.*;
import org.elte.model.zone.Empty;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Service;
import org.elte.utils.Position;
import org.elte.view.game.BuildablePanel;
import org.elte.view.game.GamePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameMouseAdapter extends MouseAdapter {
		Game game;
		GamePanel gamePanel;

		public GameMouseAdapter(Game game, GamePanel gamePanel) {
				this.game = game;
				this.gamePanel = gamePanel;
		}

		public void mouseClicked(MouseEvent e) {
				int current_x = (int) Math.floor((e.getX()) / GamePanel.FIELD_SIZE);
				int current_y = (int) Math.floor((e.getY()) / GamePanel.FIELD_SIZE);

				System.out.println("Clicked - X: " + current_x + ", Y: " + current_y);

				try {
						Position currentPosition = new Position(current_x, current_y);
						Field field = game.getField(currentPosition);
						if (BuildablePanel.getSelectedFieldZoneType() != null) {
								placeZone(current_x, current_y);
						}
						if (BuildablePanel.getSelectedBuildingType() != null) {
								Building buildingType = new org.elte.model.building.Empty(currentPosition);
								switch (BuildablePanel.getSelectedBuildingType()) {
										case ROAD -> buildingType = new Road(currentPosition);
										case EMPTY -> field.getBuilding().delete();
										case FACTORY -> buildingType = new Factory(currentPosition);
										case FIRE_STATION -> buildingType = new FireStation(currentPosition);
										case FOREST -> buildingType = new Forest(currentPosition);
										case POLICE -> buildingType = new Police(currentPosition);
										case STADIUM -> buildingType = new Stadium(currentPosition);
								}
								if (buildingType.isPlaceable() && !(buildingType instanceof org.elte.model.building.Empty)) {
										buildingType.create();
								} else {
									if (!(buildingType instanceof org.elte.model.building.Empty)) {
										System.out.println(buildingType.getBuildingType().name() + " can't be placed");
									}
								}
						}
						gamePanel.repaint();
				} catch (ArrayIndexOutOfBoundsException ex) {
						System.out.println("Clicked outside of playing area...");
						ex.printStackTrace();
				}
		}

		private void placeZone(int current_x, int current_y) {
				switch (BuildablePanel.getSelectedFieldZoneType()) {
						case EMPTY -> {
								if (!(game.getField(new Position(current_x, current_y)).getZone() instanceof Empty))
										game.getField(new Position(current_x, current_y)).setZone(new Empty(1000));
						}
						case SERVICE -> {
								if (!(game.getField(new Position(current_x, current_y)).getZone()
												instanceof Service))
										game.getField(new Position(current_x, current_y))
														.setZone(new Service(1, 2, 3, 4, 5));
						}
						case INDUSTRY -> {
								if (!(game.getField(new Position(current_x, current_y)).getZone()
												instanceof Industry))
										game.getField(new Position(current_x, current_y))
														.setZone(new Industry(1, 2, 3, 4, 5));
						}
						case RESIDENT -> {
								if (!(game.getField(new Position(current_x, current_y)).getZone()
												instanceof Resident))
										game.getField(new Position(current_x, current_y))
														.setZone(new Resident(1, 2, 3, 4, 5, null));
						}
				}
		}
}
