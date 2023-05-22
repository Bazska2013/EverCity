package org.elte.view.game;

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

import org.elte.model.Game;
import org.elte.model.TimerSpeed;
import org.elte.utils.ScreenUtils;
import org.elte.view.MainFrame;
import org.elte.view.common.buttons.IconButton;

/** A játék időgombokat megjelenítő panel osztály. */
public class TimeButtons extends JPanel {
  Game game;
  /** A képernyő mérete. */
  Dimension screenSize;

  HashMap<TimerSpeed, IconButton> iconButtons = new HashMap<>();
  /** Konstruktor az időgombok inicializálásához. */
  public TimeButtons() {
    game = Game.getInstance();
    screenSize = ScreenUtils.getScreenSize();
    this.setBounds(0, MainFrame.TIME_PANEL_Y, screenSize.width, MainFrame.TIME_PANEL_SIZE);
    this.setVisible(true);
    this.setLayout(new FlowLayout());
    createButton("II", TimerSpeed.STOP);
    createButton(">", TimerSpeed.ONE);
    createButton(">>", TimerSpeed.TWO);
    updateButtons();
  }
  /**
   * Gomb létrehozása és hozzáadása a panelhez.
   *
   * @param text a gomb szövege
   * @param timerSpeed a gombhoz tartozó idősebesség
   */
  private void createButton(String text, TimerSpeed timerSpeed) {
    IconButton button = new IconButton(text);
    button.addActionListener(
        e -> {
          game.getGameTime().setTimerSpeed(timerSpeed);
          resetAllButtons();
          updateButtons();
        });
    iconButtons.put(timerSpeed, button);
    this.add(button);
  }
  /** Az időgombok állapotának frissítése. */
  private void updateButtons() {
    if (!game.getGameTime().isRunning()) {
      iconButtons.get(TimerSpeed.STOP).setBackground(Color.GREEN);
      return;
    }
    TimerSpeed timerSpeed = TimerSpeed.valueOfDelay(game.getGameTime().getDelay());
    if (timerSpeed == null) {
      return;
    }
    iconButtons.get(timerSpeed).setBackground(Color.GREEN);
  }
  /** Az összes időgomb alaphelyzetbe állítása. */
  private void resetAllButtons() {
    iconButtons.values().forEach(IconButton::reset);
  }
}
