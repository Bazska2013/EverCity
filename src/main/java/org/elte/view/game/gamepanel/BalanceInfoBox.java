package org.elte.view.game.gamepanel;

import java.awt.*;
import javax.swing.*;

import org.elte.model.Game;
import org.elte.utils.ScreenUtils;
import org.elte.view.Localization;
import org.elte.view.game.GamePanel;

import lombok.Getter;
import lombok.Setter;

/**
 * A BalanceInfoBox osztály egy információs panelt reprezentál a játékban, amely megjeleníti a
 * játékos egyenlegét és a költségeket.
 */
@Getter
@Setter
public class BalanceInfoBox extends JPanel {
  /** A panel magassága a képernyő méretének 50%-a. */
  public static final int HEIGHT = (int) (ScreenUtils.getScreenSize().height * 0.50);
  /** A panel szélessége a képernyő szélességének 15%-a mínusz 10 pixel. */
  public static final int WIDTH = (int) (ScreenUtils.getScreenSize().width * 0.15) - 10;
  /** A panel x-koordinátája a képernyő szélességétől levonva a panel szélességét. */
  public static final int X = (ScreenUtils.getScreenSize().width) - WIDTH;
  /** A panel y-koordinátája a képernyő magasságának 10%-a. */
  public static final int Y = (int) (ScreenUtils.getScreenSize().height * 0.10);

  private Game game;
  private GamePanel gamePanel;
  /**
   * Konstruktor az BalanceInfoBox osztályhoz. Beállítja a játékot, pozíciót, méretet, keretet,
   * elrendezést és háttérszínt. Alapértelmezés szerint nem látható.
   */
  public BalanceInfoBox() {
    super();
    this.game = Game.getInstance();
    this.setBounds(X, Y, WIDTH, HEIGHT);
    this.setBorder(BorderFactory.createLineBorder(Color.black, 3));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setBackground(Color.gray);
    this.setVisible(false);
    //        reRender();
  }
  /** Újrarajzolja az információs panelt a játék aktuális állapotával. */
  public void reRender() {
    this.removeAll();
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.setBackground(Color.gray);
    int i = 1;
    JLabel balance = new JLabel();
    balance.setText(
        Localization.getProp().getProperty("balance_text") + " " + game.getBalance() + "$");
    balance.setFont(new Font("Arial", Font.BOLD, 20));
    balance.setForeground(Color.WHITE);
    contentPanel.add(balance);
    for (String s : game.getAllCosts()) {
      String[] inputs = s.split(",");
      JLabel number = new JLabel();
      number.setText(Integer.toString(i) + ":");
      contentPanel.add(number);
      i++;
      JLabel payment = new JLabel();
      payment.setText(
          Localization.getProp().getProperty(inputs[0])
              + " "
              + Localization.getProp().getProperty("price")
              + " "
              + inputs[1]
              + "$");
      payment.setFont(new Font("Arial", Font.PLAIN, 14));
      payment.setForeground(Color.WHITE);
      contentPanel.add(payment);
    }
    JScrollPane scrollPane = new JScrollPane(contentPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    scrollPane.getViewport().setBackground(Color.gray);

    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
    verticalScrollBar.setUnitIncrement(20);
    verticalScrollBar.setBlockIncrement(20);

    setLayout(new BorderLayout());
    this.add(scrollPane, BorderLayout.CENTER);
    this.setVisible(true);
  }
  /** Elrejti az információs panelt. */
  public void hideInfoBox() {
    this.removeAll();
    this.setVisible(false);
  }
  /**
   * Beállítja a játékpanelt, amelyhez a BalanceInfoBox tartozik.
   *
   * @param gamePanel a játékpanel
   */
  public void setGamepanel(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }
}
