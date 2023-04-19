package org.elte.model;

import lombok.Getter;
import org.elte.model.building.Building;
import org.elte.model.building.House;
import org.elte.model.building.Road;
import org.elte.model.zone.Resident;
import org.elte.utils.Position;
import org.elte.view.MainFrame;
import org.elte.view.MainFrame;

import java.util.Optional;

@Getter
public class Game {
  public static Game INSTANCE = null;
  public static int rows = 16;
  public static int columns = 32;
  public Field[][] fields;
  // int balance;
  private final GameTime gameTime;

  private Game() {
    fields = new Field[rows][columns];
    fillFields();
    fields[0][2].setBuilding(new Road(new Position(2,0)));
    fields[1][2].setBuilding(new Road(new Position(2,1)));
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

  public static int getRows() {
    return rows;
  }

  public static int getColumns() {
    return columns;
  }

  public Field getField(Position position) {
    if (position != null)
      return fields[position.y()][position.x()];
    return null;
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
          if (i + 1 < fields.length
                  && fields[i + 1][j].getBuilding().isEmpty()
                  && fields[i + 1][j].getZone() instanceof Resident) {
            fields[i][j].getBuilding().disableDeletable();
            return Optional.of(new Position(j, i + 1));
          }
          if (i - 1 >= 0
                  && fields[i - 1][j].getBuilding().isEmpty()
                  && fields[i - 1][j].getZone() instanceof Resident) {
            fields[i][j].getBuilding().disableDeletable();
            return Optional.of(new Position(j, i - 1));
          }
          if (j + 1 < fields[i].length
                  && fields[i][j + 1].getBuilding().isEmpty()
                  && fields[i][j + 1].getZone() instanceof Resident) {
            fields[i][j].getBuilding().disableDeletable();
            return Optional.of(new Position(j + 1, i));
          }
          if (j - 1 >= 0
                  && fields[i][j - 1].getBuilding().isEmpty()
                  && fields[i][j - 1].getZone() instanceof Resident) {
            fields[i][j].getBuilding().disableDeletable();
            return Optional.of(new Position(j - 1, i));
          }
        }
      }
    }
    return Optional.empty();
  }

  public boolean searchSurroundingBuildings(int x, int y, boolean keres, Building buildingType) {
    boolean found = false;
    for (int i = Math.max(0, x - 1); i <= Math.min(x + 1, columns - 1); i++) {
      for (int j = Math.max(0, y - 1); j <= Math.min(y + 1, rows - 1); j++) {
        if (i == x && j == y) {
          continue;
        }
        if (buildingType instanceof Road) {
          if (getField(new Position(i, j)).getBuilding() instanceof Road) {
            if (keres) {
              getField(new Position(i, j)).getBuilding().disableDeletable();
              found = true;
            } else {
              getField(new Position(i, j)).getBuilding().removeDeletable();
            }
          }
        }
      }
    }
    return found;
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
