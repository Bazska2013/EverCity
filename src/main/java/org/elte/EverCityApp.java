package org.elte;

import org.elte.model.Boostrap;
import org.elte.view.MainMenu;

public class EverCityApp {
  public static void main(String[] args) {
    MainMenu mainMenu = new MainMenu();

    Boostrap example = Boostrap.builder()
            .name("Example")
            .age(1)
            .build();

    Boostrap example2 = new Boostrap();
    example2.setAge(2);
    example2.getAge();

  }
}