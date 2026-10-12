package org.elte.model.expections;
/** Kivétel osztály, amelyet akkor dobunk, ha nincs elegendő hely az építkezéshez. */
public class NoEnoughtSpaceEx extends Exception {
  /** Üres konstruktor létrehozása a kivétel objektum példányosításához. */
  public NoEnoughtSpaceEx() {
    super();
  }
}
