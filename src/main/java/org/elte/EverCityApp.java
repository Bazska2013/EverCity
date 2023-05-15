package org.elte;

import org.elte.view.MainMenu;

/** Az EverCity játék főosztálya. */
public class EverCityApp {
  /**
   * A program belépési pontja.
   *
   * @param args a parancssori argumentumok
   */
  public static void main(String[] args) {

    try {
      MainMenu mainMenu = new MainMenu();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
