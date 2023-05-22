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
import org.elte.view.Localization;
import org.elte.view.MainFrame;
import org.elte.view.game.GamePanel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** A játékot reprezentáló osztály. */
@Getter
@Setter
@ToString
public class Game implements Serializable {

  @Serial private static final long serialVersionUID = 6529685098267757690L;
  private static final double CHANCE_TO_FIRE_NEIGHBOURS = 0.05;
  private static Game INSTANCE = null;
  public static int STARTING_MONEY = 20000;
  static final int rows = 16;
  static final int columns = 32;
  private int balance = STARTING_MONEY;
  private int mood = 50;
  public Field[][] fields;
  public List<Firetruck> firetrucks = new ArrayList<>();
  public List<Resident> residents = new ArrayList<>();
  // int balance;
  private final GameTime gameTime;
  public ArrayList<String> allCosts = new ArrayList<>();
  /** Privát konstruktor a Singleton minta implementálásához. */
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
  /** Az erdők hatását növelő metódus. */
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
  /** Kitölti a mezőket véletlenszerűen erdőkkel. */
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
  /**
   * Visszaadja a sorok számát.
   *
   * @return a sorok száma
   */
  public static int getRows() {
    return rows;
  }
  /**
   * Visszaadja az oszlopok számát.
   *
   * @return az oszlopok száma
   */
  public static int getColumns() {
    return columns;
  }
  /**
   * A játékmezőt adja vissza a megadott pozíció alapján.
   *
   * @param position a pozíció, amely alapján megadja a játékmezőt
   * @return a játékmező a megadott pozíció alapján, vagy null, ha a pozíció null
   */
  public Field getField(Position position) {
    if (position.x() > columns - 1 || position.y() > rows - 1) return null;
    if (position.x() < 0 || position.y() < 0) return null;
    if (position != null) return fields[position.y()][position.x()];
    return null;
  }
  /**
   * Próbálkozik a lakóház elhelyezésével a játékban. Keres egy olyan pozíciót a városban, ahol a
   * lakóházat el lehet helyezni, és véletlenszerűen eldönti, hogy ténylegesen elhelyezi-e.
   */
  private void tryToPlaceCitizenHouse() {
    this.findZonePositionNearStreet(FieldTypes.RESIDENT)
        .ifPresent(
            position -> {
              if ((int) (Math.random() * 2) == 1) {
                this.getField(position).setBuilding(new House(position));
              }
            });
  }
  /**
   * Kezeli a tüzet a játékban. Végigmegy az összes mezőn, és ha az épület ég, akkor keres egy olyan
   * tűzoltóautót, amely nem mozog, és a tűzzel szomszédos, majd beállítja a tűzoltóautónak a célt
   * és elindítja azt. Az épület életerejét csökkenti, majd megvizsgálja a szomszédos mezőket, és ha
   * azok megfelelően éghetők és véletlenszerűen teljesül a tűz keletkezésének esélye, akkor azokat
   * is meggyújtja. Ha az épület életereje 0 alá csökken, akkor az épületet eltávolítja.
   */
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
  /**
   * Inicializál véletlenszerű tüzeket a játékban. Végigmegy az összes mezőn, és ha az épület tud
   * égni, akkor véletlenszerűen teljesül az égés esélye, akkor az épületet kigyullad.
   */
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
  /**
   * Próbálkozik a szolgáltató épület elhelyezésével a játékban. Keres egy olyan pozíciót a
   * városban, ahol a szolgáltató épületet el lehet helyezni, és véletlenszerűen eldönti, hogy
   * ténylegesen elhelyezi-e.
   */
  private void tryToPlaceServiceBuilding() {
    this.findZonePositionNearStreet(FieldTypes.SERVICE)
        .ifPresent(
            position -> {
              if ((int) (Math.random() * 3) == 1) {
                this.getField(position).setBuilding(new ServiceBuilding(position));
              }
            });
  }
  /**
   * Keres egy olyan pozíciót a városban, ahol a megadott zóna típusával ellátott játékmező
   * szomszédos a közlekedési úthálózattal. Ha talál ilyen pozíciót, visszaadja a pozíciót, különben
   * üres opcionális értékkel tér vissza.
   *
   * @param zoneType a keresett zóna típusa
   * @return a megtalált pozíció, vagy üres opcionális érték
   */
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
  /**
   * Visszaadja az éves fenttartási díjak összegét a játékban.
   *
   * @return az éves fenttartási díjak összege
   */
  public int yearlyFee() {
    int fees = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        fees += fields[i][j].getFees();
        fees += fields[i][j].getRetiredPay();
      }
    }
    return fees;
  }
  /**
   * Visszaadja a játék példányt. Ha még nincs létrehozva példány, létrehoz egyet, majd visszaadja
   * azt.
   *
   * @return a játék példány
   */
  public static Game getInstance() {
    if (INSTANCE == null) INSTANCE = new Game();

    return INSTANCE;
  }
  /**
   * Visszaadja az éves adók összegét a játékban. Emellett kiírja az információs dobozba, hogy az
   * éves adókat fizették.
   *
   * @return az éves adók összege
   */
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

  /**
   * Megkeresi azokat a munkahelyeket, amelyek még nem teltek meg a városban.
   *
   * @return a nem teljes munkahelyek listája
   */
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
  /**
   * Keresi az állástalan lakosokat a városban, majd véletlenszerűen kijelöl egy munkahelyet
   * számukra, és beállítja nekik a munkahelyet. Az állástalan lakosokat a kiválasztott munkahelyhez
   * rendeli.
   */
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
  /** Új játékot kezd. */
  public void resetGame() {
    INSTANCE = new Game();
  }
  /**
   * Elindít egy földrengést a játékban. A földrengés során az épületek esetleges összeomlanak, és a
   * rászoruló lakosokat eltávolítják.
   */
  public void initEarthQuake() {
    Game game = Game.getInstance();
    Random rand = new Random();
    int number = rand.nextInt(0, 10000);
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
  /**
   * Keres egy helyet az embereknek, ahol lakhatnak. Az lakható helyekhez hozzáadja az új lakosokat.
   */
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
  /**
   * Fizetést hajt végre a játékban. Az összeget levonja az egyenlegeből.
   *
   * @param amount a fizetendő összeg
   */
  public void pay(int amount) {
    balance -= amount;
  }

  public void loadGame(File file) {
    if (!file.exists()) return;
    try {
      FileInputStream fileIn = new FileInputStream(file);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      INSTANCE = (Game) in.readObject();
      gameTime.setPassedDays(INSTANCE.getGameTime().getPassedDays());
      this.fields = INSTANCE.getFields();
      this.firetrucks = INSTANCE.firetrucks;
      this.balance = INSTANCE.balance;
      in.close();
      fileIn.close();
      System.out.println(INSTANCE);
    } catch (IOException i) {
      i.printStackTrace();
      return;
    } catch (ClassNotFoundException c) {
      System.out.println("Game class not found");
      c.printStackTrace();
      return;
    }
  }
  /**
   * Ellenőrzi, hogy minden még mindig összeköttetésben van-e a játékban. Az ellenőrzés során a
   * lakóövezetekben élő lakosok munkahelyeikhez való kapcsolódását vizsgálja.
   *
   * @return true, ha minden még mindig összeköttetésben van, különben false
   */
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

  public void makeResidents() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (fields[i][j].getZone() instanceof Resident) {
          residents.add((Resident) fields[i][j].getZone());
        }
      }
    }
  }

  public Resident getRandomResident() {
    makeResidents();
    Random rand = new Random();
    Resident r = residents.get(rand.nextInt(residents.size()));
    while (r.getCitizens().size() < r.getCapacity()) {
      r = residents.get(rand.nextInt(residents.size()));
    }
    return r;
  }
}
