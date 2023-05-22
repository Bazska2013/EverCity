package org.elte.view.game.gamepanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import org.elte.model.Game;
import org.elte.view.game.GamePanel;

/**
 * A GameMouseMotionListener osztály kezeli az egér mozgásával kapcsolatos eseményeket a
 * GamePanelben.
 */
public class GameMouseMotionListener implements MouseMotionListener {
  private final FieldInfoBox fieldInfoBox;
  /**
   * GameMouseMotionListener példány létrehozása a megadott FieldInfoBox objektummal.
   *
   * @param fieldInfoBox A FieldInfoBox objektum, ami a mező információs tábláját reprezentálja.
   */
  public GameMouseMotionListener(FieldInfoBox fieldInfoBox) {
    this.fieldInfoBox = fieldInfoBox;
  }
  /**
   * Az egér húzásával történő események kezelése.
   *
   * @param e Az egér húzásával kapcsolatos MouseEvent objektum.
   */
  @Override
  public void mouseDragged(MouseEvent e) {}
  /**
   * Az egér mozgásával történő események kezelése.
   *
   * @param e Az egér mozgásával kapcsolatos MouseEvent objektum.
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    int current_x = (int) Math.floor((e.getX()) / GamePanel.FIELD_SIZE);
    int current_y = (int) Math.floor((e.getY()) / GamePanel.FIELD_SIZE);

    if (checkIfCursorInsideMapBoundaries(current_x, current_y)) {}
  }
  /**
   * Ellenőrzi, hogy az egér a pálya határain belül van-e.
   *
   * @param x Az egér aktuális vízszintes pozíciója.
   * @param y Az egér aktuális függőleges pozíciója.
   * @return Igaz, ha az egér a pálya határain belül van, különben hamis.
   */
  private boolean checkIfCursorInsideMapBoundaries(int x, int y) {
    return (y >= 0) && (y < Game.getRows()) && (x >= 0) && (x < Game.getColumns());
  }
}
