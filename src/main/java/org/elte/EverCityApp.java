package org.elte;

import org.elte.model.Boostrap;
import org.elte.view.Frame;
import org.elte.view.MainMenu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EverCityApp {
  public static void main(String[] args) {
    //MainMenu mainMenu = new MainMenu();
    Frame gui = new Frame();
    try {
      gui.create();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


    Boostrap example = Boostrap.builder()
            .name("Example")
            .age(1)
            .build();

    Boostrap example2 = new Boostrap();
    example2.setAge(2);
    example2.getAge();

  }
}