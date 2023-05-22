package org.elte.utils;

import java.awt.*;

/** A ScreenUtils osztály különböző képernyővel kapcsolatos műveleteket nyújt. */
public abstract class ScreenUtils {
  /**
   * Visszaadja a képernyő méretét.
   *
   * @return a képernyő mérete
   */
  public static Dimension getScreenSize() {
    Toolkit tk = Toolkit.getDefaultToolkit();
    return tk.getScreenSize();
  }
}
