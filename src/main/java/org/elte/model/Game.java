package org.elte.model;

import lombok.Getter;

import java.awt.*;

@Getter
public class Game {
		public static Game INSTANCE = null;
		static int rows;
		static int columns;
		static int boxheight = 32;
		static Field[][] fields;
		// int balance;
		private final GameTime gameTime;

		private Game() {
				sizes();

				gameTime =
								new GameTime(
												e -> {
														// runnable code here
												});
				gameTime.setTimerSpeed(TimerSpeed.ONE);
				fields = new Field[rows][columns];
				for (int i = 0; i < rows; i++) {
						for (int j = 0; j < columns; j++) {
								fields[i][j] = new Field(i, j);
						}
				}
		}

		private void sizes() {
				Toolkit tk = Toolkit.getDefaultToolkit();
				Dimension screenSize = tk.getScreenSize();
				columns = (int) ((screenSize.width * 0.76) / 32);
				rows = (int) ((screenSize.height * 0.67) / 32);
		}

		public static int getRows() {
				return rows;
		}

		public static int getColumns() {
				return columns;
		}

		public static int getBoxheight() {
				return boxheight;
		}

		/**
		 * Getter
		 *
		 * @param i x koordináta
		 * @param j y koordináta
		 * @return Egy konkrét field-et ad vissza
		 */
		public Field getField(int i, int j) {
				return fields[i][j];
		}

		public static Game getInstance() {
				if (INSTANCE == null) INSTANCE = new Game();

				return INSTANCE;
		}
}
