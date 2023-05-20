package org.elte.model.people;

import org.elte.model.zone.Zone;

import lombok.Getter;
import lombok.Setter;

/** A Citizen osztály egy polgárt reprezentál a játékban. */
@Getter
@Setter
public class Citizen {
  /** A polgár lakhelye. */
  private Zone livestAt;
  /** A polgár munkahelye. */
  private Zone worksAt;
  /** A polgár elégedettségi szintje. */
  private int satisfaction;
  /** A polgár jövedelme. */
  private int income;
  /** A polgár életkora. */
  private int age;
  /** A polgár nyugdíjas-e. */
  private boolean isRetired = false;

  /**
   * A Citizen osztály konstruktora.
   *
   * @param livesAt a polgár lakhelye
   * @param worksAt a polgár munkahelye
   * @param age a polgár életkora
   */
  public Citizen(Zone livesAt, Zone worksAt, int age) {
    this.livestAt = livesAt;
    this.worksAt = worksAt;
    this.age = age;
    satisfaction = 50;
    income = 0;
  }
  /** Ha egy polgár elhalálozik, az adatait alaphelyzetbe állítja. */
  public void resetCitizen() {
    System.out.println("Citizen resetted...");
    satisfaction = 50;
    income = 0;
    age = 18;
    isRetired = false;
  }
  /**
   * Megadja, hogy a polgár nyugdíjas-e.
   *
   * @return true, ha a polgár 65 év feletti, false ha nem
   */
  public boolean isRetired() {
    return isRetired;
  }
}
