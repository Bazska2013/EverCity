package org.elte;

import org.elte.model.Boostrap;

public class EverCityApp {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    Boostrap example = Boostrap.builder()
            .name("Example")
            .age(1)
            .build();

    Boostrap example2 = new Boostrap();
    example2.setAge(2);
    example2.getAge();

  }
}