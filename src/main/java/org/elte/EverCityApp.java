package org.elte;

import org.elte.view.MainFrame;

public class EverCityApp {
  public static void main(String[] args) {
    // MainMenu mainMenu = new MainMenu();

    try {
      MainFrame gui = MainFrame.getInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
