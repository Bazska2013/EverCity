package org.elte.view.game.gamepanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import org.elte.model.Game;
import org.elte.view.game.GamePanel;

public class GameMouseMotionListener implements MouseMotionListener {
  private final FieldInfoBox fieldInfoBox;

  public GameMouseMotionListener(FieldInfoBox fieldInfoBox) {
    this.fieldInfoBox = fieldInfoBox;
  }

  @Override
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {
    int current_x = (int) Math.floor((e.getX()) / GamePanel.FIELD_SIZE);
    int current_y = (int) Math.floor((e.getY()) / GamePanel.FIELD_SIZE);

    if (checkIfCursorInsideMapBoundaries(current_x, current_y)) {}
  }

  private boolean checkIfCursorInsideMapBoundaries(int x, int y) {
    return (y >= 0) && (y < Game.getRows()) && (x >= 0) && (x < Game.getColumns());
  }
}
