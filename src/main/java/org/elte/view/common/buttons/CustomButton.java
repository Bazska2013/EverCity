package org.elte.view.common.buttons;

import java.awt.*;
import javax.swing.*;

/** Egyedi gombot reprezentáló osztály a felhasználói felületen. */
public class CustomButton extends JButton {
  /**
   * Konstruktor az egyedi gomb létrehozásához.
   *
   * @param text a gomb szövege
   */
  public CustomButton(String text) {
    super(text);
  }
  /**
   * A gomb háttérszínét beállító metódus.
   *
   * @param bg a háttérszín
   */
  @Override
  public void setBackground(Color bg) {
    super.setBackground(bg);
  }
}
