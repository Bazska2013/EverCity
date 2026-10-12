package org.elte.view.game.gamepanel;

import java.awt.*;
import javax.swing.*;

import org.elte.model.Game;
import org.elte.utils.ScreenUtils;

import lombok.Getter;
import lombok.Setter;

/** Az InfoBox osztály egy információs dobozt reprezentál a játékpanelen. */
@Getter
@Setter
public class InfoBox extends JPanel {
  private Timer timer;

  public static final int X = (int) (ScreenUtils.getScreenSize().width * 0.4);
  public static final int Y = 0;
  public static final int HEIGHT = (int) (ScreenUtils.getScreenSize().height * 0.05);
  public static final int WIDTH = (int) (ScreenUtils.getScreenSize().width * 0.20);
  private Game game;
  private JLabel type;
  private JLabel message;
  /** Az InfoBox osztály konstruktora. */
  public InfoBox() {
    super();
    this.game = Game.getInstance();
    this.setBounds(X, Y, WIDTH, HEIGHT);
    this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    this.setLayout(new GridLayout(1, 2));
    this.setBackground(Color.GRAY);
    this.setVisible(false);

    Font labelFont = new Font("Arial", Font.PLAIN, 12);

    type = new JLabel();
    type.setForeground(Color.white);
    type.setFont(labelFont);
    type.setHorizontalAlignment(0);
    this.add(type);

    message = new JLabel();
    message.setForeground(Color.white);
    message.setFont(labelFont);
    this.add(message);

    timer =
        new Timer(
            5000,
            e -> {
              setVisible(false);
              timer.stop();
            });
  }
  /**
   * Az InfoBox újrarajzolása a megadott adatokkal.
   *
   * @param type Az információ típusa.
   * @param message Az üzenet szövege.
   * @param color Az InfoBox hátterének színe.
   */
  public void reRender(String type, String message, Color color) {
    this.setBackground(color);
    this.type.setText(type);
    this.message.setText(message);
    setVisible(true);
    timer.restart();
  }
}
