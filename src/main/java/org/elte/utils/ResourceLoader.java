package org.elte.utils;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

import lombok.SneakyThrows;

/**
 * A ResourceLoader osztály segítséget nyújt az erőforrások betöltéséhez, például képek vagy fájlok.
 */
public abstract class ResourceLoader {
  /**
   * Betölti az adott nevű erőforrást InputStream formájában.
   *
   * @param resName az erőforrás neve
   * @return az erőforrás InputStream formában
   */
  public static InputStream loadResource(String resName) {
    return ResourceLoader.class.getClassLoader().getResourceAsStream(resName);
  }
  /**
   * Betölti az adott nevű képet Image formájában.
   *
   * @param resName a kép neve
   * @return a betöltött kép Image formában
   * @throws java.io.IOException ha hiba történik a kép betöltése közben
   */
  @SneakyThrows
  public static Image loadImage(String resName) {
    URL url = ResourceLoader.class.getClassLoader().getResource(resName);
    return ImageIO.read(url);
  }
}
