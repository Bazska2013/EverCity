package org.elte.view.common.buttons;

import java.awt.*;

/** Szöveges gombot reprezentáló osztály a felhasználói felületen. */
public class TextButton extends CustomButton {
  /**
   * Konstruktor a szöveges gomb létrehozásához.
   *
   * @param text a szöveges gomb szövege
   */
  public TextButton(String text) {
    super(text);
    this.setBackground(new Color(3, 193, 60));
    this.setForeground(Color.WHITE);
    this.setFocusPainted(false);
    this.setFont(new Font("Tahoma", Font.BOLD, 12));
  }
}
