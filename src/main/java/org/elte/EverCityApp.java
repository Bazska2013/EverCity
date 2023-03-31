package org.elte;

import org.elte.view.Frame;

import java.io.IOException;

public class EverCityApp {
  public static void main(String[] args) {
    //MainMenu mainMenu = new MainMenu();
    Frame gui = new Frame();
    try {
      gui.create();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}