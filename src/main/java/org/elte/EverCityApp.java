package org.elte;

import org.elte.view.MainFrame;

/** Az EverCity játék főosztálya. */
public class EverCityApp {
  /**
   * A program belépési pontja.
   *
   * @param args a parancssori argumentumok
   */
  public static void main(String[] args) {
    // MainMenu mainMenu = new MainMenu();

    try {
      MainFrame gui = MainFrame.getInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
