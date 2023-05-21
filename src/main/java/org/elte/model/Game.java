package org.elte.model;

import java.awt.*;
import java.util.*;
import java.util.List;

import org.elte.model.building.*;
import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Service;
import org.elte.model.zone.Zone;
import org.elte.utils.Position;
import org.elte.view.Localization;
import org.elte.view.MainFrame;
import org.elte.view.game.GamePanel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
  private static final double CHANCE_TO_FIRE_NEIGHBOURS = 0.05;
  private static Game INSTANCE = null;
  static final int rows = 16;
  static final int columns = 32;
  private int balance = 20000;
  private int mood = 50;
  public Field[][] fields;
  public List<Firetruck> firetrucks = new ArrayList<>();
  // int balance;
  private final GameTime gameTime;
  public ArrayList<String> allCosts = new ArrayList<>();

  private Game() {
    fields = new Field[rows][columns];
    fillFields();
    fields[0][2].setZone(new org.elte.model.zone.Road(new Position(2, 0)));
    fields[0][2].setBuilding(new Road(new Position(2, 0)));
    fields[1][2].setZone(new org.elte.model.zone.Road(new Position(2, 1)));
    fields[1][2].setBuilding(new Road(new Position(2, 1)));
    gameTime =
        new GameTime(
            e -> {
              tryToPlaceCitizenHouse();
              tryToPlaceServiceBuilding();
              initRandomFire();
              initEarthQuake();
              handleFire();
              searchUnemployedCitizens();
              searchPlaceToLive();
              setMood(allHappiness());
              if (getGameTime().overAYear()) {
                balance += yearlyTax();
                allCosts.add("evesado" + "," + yearlyTax());
                balance -= yearlyFee();
                if (balance > 0) {
                  GameTime.changeBalanceYears(false);
                } else {
                  GameTime.changeBalanceYears(true);
                }
                allCosts.add("evesfentdij" + "," + yearlyFee());
                increaseForestImpacts();
                System.out.println(allCosts.toString());
              }
            });
    gameTime.setTimerSpeed(TimerSpeed.ONE);
  }

  private void increaseForestImpacts() {
    for (Field[] fieldRow : this.fields) {
      for (Field field : fieldRow) {
        if (field.getBuilding() instanceof Forest) {
          Forest forest = (Forest) field.getBuilding();
          forest.setSize(forest.getSize() + 1);
        }
      }
    }
  }

  public void fillFields() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Random rand = new Random();
        fields[i][j] = new Field(new Position(j, i));
        if (Math.floorMod(rand.nextInt(), 100) < 5)
          fields[i][j].setBuilding(new Forest(new Position(j, i)));
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
    if (position != null) return fields[position.y()][position.x()];
    return null;
  }

  private void tryToPlaceCitizenHouse() {
    this.findZonePositionNearStreet(FieldTypes.RESIDENT)
        .ifPresent(
            position -> {
              if ((int) (Math.random() * 2) == 1) {
                this.getField(position).setBuilding(new House(position));
              }
            });
  }

  private void handleFire() {
    for (Field[] fieldRow : this.fields) {
      for (Field field : fieldRow) {
        if (field.getBuilding().isBurning()) {

          firetrucks.stream()
              .filter(
                  firetruck ->
                      !firetruck.isMoving() && field.isConnectedWith(firetruck.getPosition()))
              .findFirst()
              .ifPresent(
                  firetruck -> {
                    firetruck.setTarget(field.getPosition());
                    firetruck.start();
                  });
          field.getBuilding().setHealth(field.getBuilding().getHealth() - 1);
          List<Field> connectedFields = field.getNeighbors();
          for (Field connectedField : connectedFields) {

            if (!connectedField.getBuilding().isBurning()
                && connectedField.getBuilding().canBurn()) {
              if (Math.random() < CHANCE_TO_FIRE_NEIGHBOURS) {
                connectedField.getBuilding().setBurning(true);
                GamePanel.infoBox.reRender(
                    Localization.getProp().getProperty("warning"),
                    Localization.getProp().getProperty("building_on_fire"),
                    Color.ORANGE);
              }
            }
          }
          if (field.getBuilding().getHealth() < 1) {
            field.getBuilding().delete();
            GamePanel.infoBox.reRender(
                Localization.getProp().getProperty("warning"),
                Localization.getProp().getProperty("building_burned_down"),
                Color.RED);
          }
        }
      }
    }
  }

  private void initRandomFire() {
    for (Field[] fieldRow : this.fields) {
      for (Field field : fieldRow) {
        double chanceToFire = field.getBuilding().getChanceToFire();
        double randomNumber = Math.random();
        if (field.getBuilding().canBurn() && randomNumber < chanceToFire) {
          field.getBuilding().setBurning(true);
          GamePanel.infoBox.reRender(
              Localization.getProp().getProperty("warning"),
              Localization.getProp().getProperty("building_on_fire"),
              Color.ORANGE);
        }
      }
    }
  }

  private void tryToPlaceServiceBuilding() {
    this.findZonePositionNearStreet(FieldTypes.SERVICE)
        .ifPresent(
            position -> {
              if ((int) (Math.random() * 3) == 1) {
                this.getField(position).setBuilding(new ServiceBuilding(position));
              }
            });
  }

  public Optional<Position> findZonePositionNearStreet(FieldTypes zoneType) {
    for (int i = 0; i < fields.length; i++) {
      for (int j = 0; j < fields[i].length; j++) {
        if (fields[i][j].getBuilding().isRoad()) {
          if (i + 1 < fields.length
              && fields[i + 1][j].getBuilding().isEmpty()
              && fields[i + 1][j].getZone().getZoneType() == zoneType) {
            return Optional.of(new Position(j, i + 1));
          }
          if (i - 1 >= 0
              && fields[i - 1][j].getBuilding().isEmpty()
              && fields[i - 1][j].getZone().getZoneType() == zoneType) {
            return Optional.of(new Position(j, i - 1));
          }
          if (j + 1 < fields[i].length
              && fields[i][j + 1].getBuilding().isEmpty()
              && fields[i][j + 1].getZone().getZoneType() == zoneType) {
            return Optional.of(new Position(j + 1, i));
          }
          if (j - 1 >= 0
              && fields[i][j - 1].getBuilding().isEmpty()
              && fields[i][j - 1].getZone().getZoneType() == zoneType) {
            return Optional.of(new Position(j - 1, i));
          }
        }
      }
    }
    return Optional.empty();
  }

  public int yearlyFee() {
    int fees = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        fees += fields[i][j].getFees();
      }
    }
    return fees;
  }

  public static Game getInstance() {
    if (INSTANCE == null) INSTANCE = new Game();

    return INSTANCE;
  }

  public int yearlyTax() {
    int fees = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        fees += fields[i][j].getTaxes();
      }
    }
    GamePanel.infoBox.reRender(
        Localization.getProp().getProperty("attention"),
        Localization.getProp().getProperty("evesado")
            + " "
            + Localization.getProp().getProperty("paid"),
        Color.BLUE);
    return fees;
  }

  public static int indServBalance() {
    int ind = 0;
    int serv = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        /*if(fields[i][j].getZone()==Industry){
          ind++;
        }
        if(fields[i][j].getZone()==Service){
          serv++;
        }*/
      }
    }
    if (Math.abs(ind - serv) > 6) {
      return -10;
    } else if (Math.abs(ind - serv) > 3) {
      return -5;
    }
    return 0;
  }

  public int allHappiness() {
    int happy = 0;
    int buildingnumber = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (fields[i][j].getZone() instanceof Resident
            || fields[i][j].getZone() instanceof Industry
            || fields[i][j].getZone() instanceof Service) {
          buildingnumber++;
        }
        fields[i][j].affects();
        happy += fields[i][j].getZone().getHapiness();
      }
    }
    if (buildingnumber == 0 || happy / buildingnumber <= 0) {
      return 0;
    }
    if (happy / buildingnumber >= 100) {
      return 100;
    }
    return (happy / buildingnumber);
  }

  public int cityCapacity() {
    int count = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        count += fields[i][j].getZone().getCapacity();
      }
    }
    return count;
  }

  public int populationRate() {
    return 0;
  }

  public int balanceAffect() {
    if (balance < 0) {
      if ((balance % 10) == 0) {
        if (GameTime.affectBalanceYears() == 0) {
          return 5;
        } else if (GameTime.affectBalanceYears() == 3) {
          return 10;
        } else {
          return 15;
        }
      } else if ((balance % 100) == 0) {
        if (GameTime.affectBalanceYears() == 0) {
          return 10;
        } else if (GameTime.affectBalanceYears() == 3) {
          return 15;
        } else {
          return 20;
        }
      } else {
        if (GameTime.affectBalanceYears() == 0) {
          return 15;
        } else if (GameTime.affectBalanceYears() == 3) {
          return 20;
        } else {
          return 25;
        }
      }
    }
    return 0;
  }

  public List<Zone> findNotFullWorkplaces() {
    List<Zone> workplaces = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if ((fields[i][j].getZone() instanceof Industry
                || fields[i][j].getZone() instanceof Service)
            && (fields[i][j].getBuilding() instanceof Factory
                || fields[i][j].getBuilding() instanceof ServiceBuilding)) {
          if (fields[i][j].getZone().getCitizenNumber() < fields[i][j].getZone().getCapacity()) {
            workplaces.add(fields[i][j].getZone());
          }
        }
      }
    }
    return workplaces;
  }

  public void searchUnemployedCitizens() {
    List<Citizen> joblessCitizens = new ArrayList<>();
    List<Zone> emptyWorkplaces = findNotFullWorkplaces();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Field field = fields[i][j];
        if (field.getZone() instanceof Resident && field.getBuilding() instanceof House) {
          for (Citizen c : ((Resident) field.getZone()).getCitizens()) {
            if (c.getWorksAt() == null) {
              joblessCitizens.add(c);
            }
          }
        }
      }
    }
    assignWorkplaces(joblessCitizens, emptyWorkplaces);
  }

  public void assignWorkplaces(List<Citizen> joblessCitizens, List<Zone> emptyWorkplaces) {
    if (joblessCitizens != null && joblessCitizens.size() != 0) {
      for (Citizen c : joblessCitizens) {
        if (emptyWorkplaces == null || emptyWorkplaces.size() == 0) {
          break;
        } else {
          Random rand = new Random();
          int rand_i = rand.nextInt(emptyWorkplaces.size());
          if (emptyWorkplaces.get(rand_i).getCapacity()
              > emptyWorkplaces.get(rand_i).getWorkers().size()) {
            c.setWorksAt(emptyWorkplaces.get(rand_i));
            emptyWorkplaces.get(rand_i).addCitizen(c);
          }
        }
      }
    }
  }

  public void resetGame() {
    INSTANCE = new Game();
  }

  public void initEarthQuake() {
    Game game = Game.getInstance();
    Random rand = new Random();
    int number = rand.nextInt(0, 100000);
    int house_counter = 0;
    if (number < 50) {
      for (Field[] fieldRow : this.fields) {
        for (Field field : fieldRow) {
          double chanceToCollapse = field.getBuilding().getChanceToCollapse();
          double randomNumber = Math.random();
          if (randomNumber < chanceToCollapse) {
            field
                .getBuilding()
                .getFieldPositions()
                .forEach(
                    pos -> {
                      Field currentField = game.getField(pos);
                      currentField.removeCitizens();
                      currentField.setBuilding(new Rubble(pos));
                      MainFrame mainFrame = MainFrame.getInstance();
                      mainFrame.repaintCitizenNumber();
                    });
            game.getField(field.getPosition()).removeCitizens();
            MainFrame mainFrame = MainFrame.getInstance();
            mainFrame.repaintCitizenNumber();
            game.getField(field.getPosition()).setBuilding((new Rubble(field.getPosition())));
            house_counter++;
          }
        }
      }
      GamePanel.infoBox.reRender(
          Localization.getProp().getProperty("earthquake"),
          house_counter + " " + Localization.getProp().getProperty("house_collapsed"),
          Color.RED);
    }
  }

  public void searchPlaceToLive() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (fields[i][j].getZone() instanceof Resident
            && fields[i][j].getBuilding() instanceof House) {
          if (fields[i][j].getZone().getCitizenNumber() < fields[i][j].getZone().getCapacity()) {
            fields[i][j]
                .getZone()
                .addCitizen(
                    new Citizen(
                        fields[i][j].getZone(),
                        null,
                        Math.floorMod(new Random().nextInt(), 43) + 18));
            MainFrame mainframe = MainFrame.getInstance();
            mainframe.repaintCitizenNumber();
          }
        }
      }
    }
  }

  public void pay(int amount) {
    balance -= amount;
  }

  public boolean everythingStillConnected() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (fields[i][j].getZone() instanceof Resident
            && fields[i][j].getBuilding() instanceof House) {
          for (Citizen c : ((Resident) fields[i][j].getZone()).getCitizens()) {
            if (c.getWorksAt() != null) {
              if (!fields[i][j].isConnectedWith(c.getWorksAt().getPosition())) {
                return false;
              }
            }
          }
        }
      }
    }
    return true;
  }
}
