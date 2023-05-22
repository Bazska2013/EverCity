package org.elte.model;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.*;

import lombok.Getter;
import lombok.Setter;

/** A játék időtartamát reprezentáló osztály, amely a játék időmúlását és dátumát kezeli. */
@Getter
@Setter
public class GameTime extends Timer {
  private int passedDays = 0;
  private static final int START_YEAR = 2023;
  private static int negativeBalanceYears = 0;
  /**
   * Konstruktor a GameTime objektum létrehozásához.
   *
   * @param listener az eseménykezelő, amelyet a Timer eseményeihez rendelünk
   */
  public GameTime(ActionListener listener) {
    super(TimerSpeed.STOP.getValue(), listener);
    super.addActionListener(e -> passedDays++);
    super.setRepeats(true);
    super.start();
  }

  @Override
  public String toString() {
    return LocalDate.of(START_YEAR, 1, 1).plusDays(getPassedDays()).toString();
  }
  /**
   * Beállítja a Timer sebességét a megadott TimerSpeed érték alapján.
   *
   * @param timerSpeed a Timer sebességének beállításához használt TimerSpeed érték
   */
  public void setTimerSpeed(TimerSpeed timerSpeed) {
    if (timerSpeed.getValue() < 0) {
      this.stop();
      return;
    }
    if (!this.isRunning()) {
      this.restart();
    }
    this.setDelay(timerSpeed.getValue());
  }

  public LocalDate getDate() {
    return LocalDate.of(START_YEAR, 1, 1).plusDays(getPassedDays());
  }
  /**
   * Ellenőrzi, hogy eltelt-e egy év.
   *
   * @return true, ha igen, különben false
   */
  public boolean overAYear() {
    if ((getPassedDays() % 365) == 0) {
      return true;
    }
    return false;
  }public boolean passed300Days() {
    if ((passedDays % 10) == 0) {
      return true;
    }
    return false;
  }

  public static void changeBalanceYears(boolean neg) {
    if (neg) {
      negativeBalanceYears++;
    } else {
      if (negativeBalanceYears > 0) {
        negativeBalanceYears = 0;
      }
    }
  }

  public static int affectBalanceYears() {
    if (negativeBalanceYears <= 5) {
      return 3;
    } else if (negativeBalanceYears > 5) {
      return 5;
    }
    return 0;
  }
}
