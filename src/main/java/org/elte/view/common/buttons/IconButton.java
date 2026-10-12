package org.elte.view.common.buttons;

import java.awt.*;

/** Ikon gombot reprezentáló osztály a felhasználói felületen. */
public class IconButton extends CustomButton {
  /**
   * Konstruktor az ikon gomb létrehozásához.
   *
   * @param text az ikon gomb szövege
   */
  public IconButton(String text) {
    super(text);
    reset();
  }
  /** Visszaállítja az ikon gomb alapértelmezett beállításait. */
  public void reset() {
    this.setBorderPainted(false);
    this.setFocusPainted(false);
    this.setContentAreaFilled(true);
    this.setBackground(Color.WHITE);
  }
  /** Ellenőrzi az ikon gomb állapotát. */
  public void check() {}
}
