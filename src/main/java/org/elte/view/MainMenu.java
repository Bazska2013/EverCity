package org.elte.view;

import java.awt.*;
import javax.swing.*;

import org.elte.view.common.buttons.CustomButton;

/** A főmenüt megjelenítő osztály. */
public class MainMenu {
  public static String GAME_TITLE = "EverCity";
  public static String CONTINUE_GAME = "Játék folytatása";
  public static String NEW_GAME = "Új játék";
  public static String EXIT_GAME = "Kilépés";
  /** Konstruktor az osztályhoz. Létrehozza és megjeleníti a főmenüt. */
  public MainMenu() {
    JFrame frame = new JFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new JPanel());
    frame.add(getMenu());
    frame.setLayout(new GridLayout(0, 3));
    frame.setSize(500, 500);
    frame.setTitle(GAME_TITLE);
    frame.setVisible(true);
  }
  /**
   * A címet tartalmazó címke létrehozása.
   *
   * @return a címkét tartalmazó címke
   */
  private JLabel getTitle() {
    JLabel title = new JLabel(GAME_TITLE);
    title.setFont(new Font("Verdana", Font.PLAIN, 20));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    return title;
  }
  /**
   * A menüt tartalmazó panel létrehozása.
   *
   * @return a menüt tartalmazó panel
   */
  private JPanel getMenu() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JButton continueGameBtn = new CustomButton(CONTINUE_GAME);
    JButton newGameBtn = new CustomButton(NEW_GAME);
    JButton exitBtn = new CustomButton(EXIT_GAME);
    newGameBtn.addActionListener(
        e -> {
          MainFrame.newInstance();
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
}
