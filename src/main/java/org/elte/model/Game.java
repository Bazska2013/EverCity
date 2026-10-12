package org.elte.model;

import java.awt.*;
import java.io.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

import org.elte.model.building.*;
import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Service;
import org.elte.model.zone.Zone;
import org.elte.utils.Position;
import org.elte.view.MainFrame;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** A játékot reprezentáló osztály. */
@Getter
public class Game {
		public static Game INSTANCE = null;
		static int rows;
		static int columns;
		static int boxHeight = 32;
		static int boxWidth = 32;
		public Field[][] fields;
		// int balance;
		private final GameTime gameTime;

		private Game() {
				sizes();

				fields = new Field[rows][columns];
				fillFields();
				fields[0][2].setBuilding(new Road());
				fields[1][2].setBuilding(new Road());
				gameTime =
								new GameTime(
												e -> {
														tryToPlaceCitizenHouse();
														if(getGameTime().overAYear()){
															MainFrame.SetBalance(MainFrame.GetBalance()-yearlyFee());
															MainFrame.SetBalance(MainFrame.GetBalance()+yearlyTax());
														}
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
				return boxHeight;
		}

		public static int getBoxWidth() {
				return boxWidth;
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

		public int yearlyFee(){
			int fees=0;
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					fees+=fields[i][j].getFees();
				}
			}
			return fees;
		}
		public int yearlyTax(){
			int fees=0;
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					fees+=fields[i][j].getTaxes();
				}
			}
			return fees;
		}
}
