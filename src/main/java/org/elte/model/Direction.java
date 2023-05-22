package org.elte.model;

import org.elte.utils.Position;

/** A mozgás irányát reprezentáló felsorolási típus. */
public enum Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT;
  /**
   * Az irányt megadó statikus metódus, amely két pozíció közötti irányt számítja ki.
   *
   * @param from a kiinduló pozíció.
   * @param to a célpont pozíció.
   * @return a kiszámított irány.
   */
  public static Direction getDirection(Position from, Position to) {
    int dx = to.x() - from.x();
    int dy = to.y() - from.y();
    if (dx == 0 && dy == 0) {
      // from and to are the same position
      return null;
    }
    if (Math.abs(dx) > Math.abs(dy)) {
      // horizontal movement
      return dx > 0 ? RIGHT : LEFT;
    } else {
      // vertical movement
      return dy > 0 ? DOWN : UP;
    }
  }
}
