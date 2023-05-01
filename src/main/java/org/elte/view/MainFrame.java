package org.elte.view;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.enums.LanguageEnum;
import org.elte.model.people.Citizen;
import org.elte.model.zone.Resident;
import org.elte.utils.Position;
import org.elte.utils.ScreenUtils;
import org.elte.view.game.BuildablePanel;
import org.elte.view.game.GamePanel;
import org.elte.view.game.TimeButtons;
import org.elte.view.game.gamepanel.FieldInfoBox;

import lombok.Setter;

@Setter
public class MainFrame {
  public static String language = "hu_HU";
  public static final int ZONE_BOX_Y = (int) (ScreenUtils.getScreenSize().height * 0.75);
  public static final int ZONE_BOX_SIZE = (ScreenUtils.getScreenSize().height - ZONE_BOX_Y);
  public static final int TIME_PANEL_SIZE = 30;
  public static final int TIME_PANEL_Y =
      ScreenUtils.getScreenSize().height - ZONE_BOX_SIZE - TIME_PANEL_SIZE;
  private static final int RERENDER_INTERVAL = 500;
  public static final String BALANCE_CURRENCY = "$";
  private final JFrame frame;
  private static GamePanel gamePanel;
  private final TimeButtons timeButtons;
  private final BuildablePanel buildablePanel;
  private final FieldInfoBox fieldInfoBox;
  private final Game game;
  private int currentYear;
  public static int startingCitizensToAdd = 50;
  JMenu timeMenu;
  Timer timer;
  Dimension screenSize;

  private static MainFrame instance = null;
  private JMenu balanceMenu;
  private JMenu populationBar;

  private MainFrame() {
    frame = new JFrame("EverCity");
    screenSize = ScreenUtils.getScreenSize();
    game = Game.getInstance();
    currentYear = game.getGameTime().getDate().getYear();

    frame.setJMenuBar(createMenuBar());

    frame.setSize(screenSize.width, screenSize.height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setLocationRelativeTo(null);
    frame.setLayout(null);
    frame.setResizable(false);

    timeButtons = new TimeButtons();
    frame.add(timeButtons);

    buildablePanel = new BuildablePanel();
    frame.add(buildablePanel);

    fieldInfoBox = new FieldInfoBox();
    frame.add(fieldInfoBox);

    gamePanel = new GamePanel(fieldInfoBox);
    frame.add(gamePanel);

    frame.requestFocus();
    frame.setVisible(true);
    timer =
        new Timer(
            RERENDER_INTERVAL,
            e -> {
              updateTimer();
              gamePanel.repaint();
              updateBalance();
            });

    timer.start();
    timer.setRepeats(true);
  }

  public static MainFrame getInstance() {
    if (instance == null) {
      instance = new MainFrame();
    }
    return instance;
  }

  public static void newInstance() {
    instance = new MainFrame();
  }

  private JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu gameOptions = new JMenu(Localization.getProp().getProperty("menu_text"));
    // menu.setIcon(new ImageIcon(icon));
    JMenuItem newGame = new JMenuItem(Localization.getProp().getProperty("new_game_text"));
    gameOptions.add(newGame);

    JMenu languageSelectorMenuItem =
        new JMenu(Localization.getProp().getProperty("language_title"));
    JMenuItem hungarianLanguageSelectorItem =
        new JMenuItem(Localization.getProp().getProperty("hungarian"));
    hungarianLanguageSelectorItem.addActionListener(
        e -> {
          MainFrame.language = LanguageEnum.hu_HU.name();
          Localization.reloadProp();
          frame.setJMenuBar(createMenuBar());
          frame.repaint();
        });
    JMenuItem englishLanguageSelectorItem =
        new JMenuItem(Localization.getProp().getProperty("english"));
    englishLanguageSelectorItem.addActionListener(
        e -> {
          MainFrame.language = LanguageEnum.en_GB.name();
          Localization.reloadProp();
          frame.setJMenuBar(createMenuBar());
          frame.repaint();
        });
    languageSelectorMenuItem.add(hungarianLanguageSelectorItem);
    languageSelectorMenuItem.add(englishLanguageSelectorItem);
    gameOptions.add(languageSelectorMenuItem);

    JMenuItem exitMenuItem = new JMenuItem(Localization.getProp().getProperty("exit_text"));
    exitMenuItem.addActionListener(ae -> System.exit(0));
    gameOptions.add(exitMenuItem);

    menuBar.add(gameOptions);

    menuBar.add(Box.createHorizontalStrut(30));
    this.balanceMenu = new JMenu();
    menuBar.add(this.balanceMenu);

    this.populationBar = new JMenu(Localization.getProp().getProperty("population") + " " + 0);
    populationBar.setEnabled(false);
    menuBar.add(populationBar);

    menuBar.add(Box.createHorizontalStrut(50));

    JMenu moodLevel = new JMenu(Localization.getProp().getProperty("mood_level_text"));
    balanceMenu.setEnabled(false);
    menuBar.add(moodLevel);

    menuBar.add(Box.createHorizontalGlue());
    timeMenu = new JMenu(game.getGameTime().toString());
    timeMenu.setEnabled(false);
    menuBar.add(timeMenu);
    return menuBar;
  }

  private void updateTimer() {
    timeMenu.setText(game.getGameTime().toString());
    if (game.getGameTime().getDate().getYear() > currentYear) {
      Random rand = new Random();
      for (Field[] fields : game.getFields()) {
        for (Field field : fields) {
          if (field.getZone() instanceof Resident) {
            for (Citizen cit : ((Resident) field.getZone()).getCitizens()) {
              cit.setAge(cit.getAge() + 1);
              System.out.println(cit.getAge());
              if (cit.getAge() >= 65) {
                cit.setWorksAt(null);
                cit.setRetired(true);
                if (cit.getAge() > 65 + Math.floorMod(rand.nextInt(), 36)) {
                  cit.resetCitizen();
                }
              }
            }
          }
        }
      }
      currentYear++;
    }
  }

  private void updateBalance() {
    balanceMenu.setText(
        Localization.getProp().getProperty("balance_text") + game.getBalance() + BALANCE_CURRENCY);
  }

  public static GamePanel getGamePanel() {
    return gamePanel;
  }

  public void repaintCitizenNumber() {
    int citizenNumber = 0;
    for (int i = 0; i < Game.getColumns(); i++) {
      for (int j = 0; j < Game.getRows(); j++) {
        if (game.getField(new Position(i, j)).getZone() instanceof Resident) {
          citizenNumber +=
              ((Resident) game.getField(new Position(i, j)).getZone()).getCitizens().size();
        }
      }
    }

    populationBar.setText(Localization.getProp().getProperty("population") + " " + citizenNumber);
  }
}
