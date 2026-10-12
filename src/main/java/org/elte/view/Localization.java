package org.elte.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** A nyelvi lokalizációért felelős osztály. */
public class Localization {

  private static Localization instance = null;
  private static Properties prop = null;
  /** Privát konstruktor a singleton osztályhoz. */
  private Localization() {

    try {
      InputStream input =
          new FileInputStream(
              "./src/main/resources/localization/localization_"
                  + MainFrame.language
                  + ".properties");
      prop = new Properties();
      prop.load(input);

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
  /**
   * Az osztály példányának lekérdezése.
   *
   * @return az osztály példánya
   */
  public static Properties getProp() {
    if (instance == null) {
      instance = new Localization();
    }
    return prop;
  }
  /** Az osztály példányának újratöltése. */
  public static void reloadProp() {
    instance = new Localization();
  }
}
