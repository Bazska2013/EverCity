package org.elte.model;

import lombok.Getter;
import org.elte.model.building.Field;
import org.elte.model.building.House;
import org.elte.model.building.Road;
import org.elte.utils.Position;

import java.util.Optional;

@Getter
public class Game {
		public static Game INSTANCE = null;
		static int rows = 16;
		static int columns = 32;
		public Field[][] fields;
		// int balance;
		private final GameTime gameTime;

		private Game() {
				fields = new Field[rows][columns];
				fillFields();
				fields[0][2].setBuilding(new Road());
				fields[1][2].setBuilding(new Road());
				gameTime =
								new GameTime(
												e -> {
														tryToPlaceCitizenHouse();
												});
				gameTime.setTimerSpeed(TimerSpeed.ONE);
		}

		public void fillFields() {
				for (int i = 0; i < rows; i++) {
						for (int j = 0; j < columns; j++) {
								fields[i][j] = new Field(i, j);
						}
				}
		}


		public static int getRows() {
				return rows;
		}

		public static int getColumns() {
				return columns;
		}


		public Field getField(Position position) {
				return fields[position.y()][position.x()];
		}

		private void tryToPlaceCitizenHouse() {
				this.findNewCitizenHousePosition()
								.ifPresent(
												position -> {
														if ((int) (Math.random() * 2) == 1) {
																this.getField(position).setBuilding(new House());
														}
												});
		}

		public Optional<Position> findNewCitizenHousePosition() {
				for (int i = 0; i < fields.length; i++) {
						for (int j = 0; j < fields[i].length; j++) {
								if (fields[i][j].getBuilding().isRoad()) {
										if (i + 1 < fields.length && fields[i + 1][j].getBuilding().isEmpty()) {
												return Optional.of(new Position(j, i + 1));
										}
										if (i - 1 >= 0 && fields[i - 1][j].getBuilding().isEmpty()) {
												return Optional.of(new Position(j, i - 1));
										}
										if (j + 1 < fields[i].length && fields[i][j + 1].getBuilding().isEmpty()) {
												return Optional.of(new Position(j + 1, i));
										}
										if (j - 1 >= 0 && fields[i][j - 1].getBuilding().isEmpty()) {
												return Optional.of(new Position(j - 1, i));
										}
								}
						}
				}
				return Optional.empty();
		}

		public static Game getInstance() {
				if (INSTANCE == null) INSTANCE = new Game();

				return INSTANCE;
		}
}
