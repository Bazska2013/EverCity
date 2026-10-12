package org.elte.view;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import javax.swing.*;

import org.elte.model.Game;
import org.elte.view.common.buttons.CustomButton;

/** A főmenüt megjelenítő osztály. */
public class MainMenu {
  public static final String SAVES_FOLDER_PATH = "./saves/";
  public static String GAME_TITLE = "EverCity";
  public static String CONTINUE_GAME = "Játék folytatása";
  public static String NEW_GAME = "Új játék";
  public static String EXIT_GAME = "Kilépés";
  public static String GET_GAME_NAME = "Kerlek add meg a pálya nevét: ";

  public MainMenu() {
    JFrame frame = new JFrame();
    createSavesFolder();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new JPanel());
    frame.add(getMenu());
    frame.setLayout(new GridLayout(0, 3));
    frame.setSize(500, 500);
    frame.setTitle(GAME_TITLE);
    frame.setVisible(true);
  }

  private void createSavesFolder() {
    File savesFolder = new File(SAVES_FOLDER_PATH);
    if (!savesFolder.exists()) {
      boolean folderCreated = savesFolder.mkdir();
      if (!folderCreated) {
        System.out.println("Failed to create the 'saves' folder.");
      }
    }
  }

  private JLabel getTitle() {
    JLabel title = new JLabel(GAME_TITLE);
    title.setFont(new Font("Verdana", Font.PLAIN, 20));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    return title;
  }

  private JPanel getMenu() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JButton continueGameBtn = new CustomButton(CONTINUE_GAME);
    JButton newGameBtn = new CustomButton(NEW_GAME);
    JButton exitBtn = new CustomButton(EXIT_GAME);
    continueGameBtn.addActionListener(
        e -> {
          File[] saves = getSaves();
          Object[] saveNames =
              Arrays.stream(saves)
                  .map(file -> Arrays.stream(file.getName().split("\\.")).toList().get(0))
                  .toArray();
          String selectedSave =
              (String)
                  JOptionPane.showInputDialog(
                      panel,
                      "Válaszd ki melyik mentést szeretnéd betölteni",
                      "Játék folyatása",
                      JOptionPane.PLAIN_MESSAGE,
                      null,
                      saveNames,
                      saveNames[0]);
          MainFrame.newInstance(selectedSave);
          Game.getInstance().loadGame(new File(SAVES_FOLDER_PATH + selectedSave + ".ser"));
        });
    newGameBtn.addActionListener(
        e -> {
          String gameName =
              (String)
                  JOptionPane.showInputDialog(
                      panel, GET_GAME_NAME, "Új játék", JOptionPane.PLAIN_MESSAGE, null, null, "");
          MainFrame.newInstance(gameName);
        });
    exitBtn.addActionListener(e -> System.exit(0));
    continueGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    newGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    Dimension buttonSize = new Dimension(200, continueGameBtn.getPreferredSize().height);
    continueGameBtn.setMaximumSize(buttonSize);
    newGameBtn.setMaximumSize(buttonSize);
    exitBtn.setMaximumSize(buttonSize);

    panel.add(Box.createVerticalGlue());
    panel.add(getTitle());
    panel.add(Box.createVerticalStrut(20));
    panel.add(continueGameBtn);
    panel.add(Box.createVerticalStrut(10));
    panel.add(newGameBtn);
    panel.add(Box.createVerticalStrut(10));
    panel.add(exitBtn);
    panel.add(Box.createVerticalGlue());
    panel.add(Box.createVerticalGlue());

    panel.setAlignmentX(Component.CENTER_ALIGNMENT);
    return panel;
  }

  private File[] getSaves() {
    File savesFolder = new File(SAVES_FOLDER_PATH);
    return savesFolder.listFiles();
  }
}
