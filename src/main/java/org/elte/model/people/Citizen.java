package org.elte.model.people;

import java.awt.geom.Point2D;

import org.elte.model.zone.Zone;

import lombok.Getter;
import lombok.Setter;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Zone;
import java.util.Random;


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
    Random rand = new Random();
    this.livestAt = livesAt;
    this.worksAt = worksAt;
    this.age = age;
    satisfaction = 50;
    income = rand.nextInt(30);
  }


  /** Ha egy polgár elhalálozik, az adatait alaphelyzetbe állítja. */
  public void resetCitizen(Resident r) {
    System.out.println("Citizen resetted...");
    satisfaction = 50;
    income = 0;
    age = 18;
    isRetired = false;
    livestAt=r;
  }

  /**
   * Megadja, hogy a polgár nyugdíjas-e.
   *
   * @return true, ha a polgár 65 év feletti, false ha nem
   */
  public boolean isRetired() {
    return isRetired;
  }

  public void below65() {
    if (age > 65) {
      isRetired = true;
    }
  }

  public int workHomeDistance() {
    if (worksAt != null) {
      double distance =
          Point2D.distance(
              worksAt.getPosition().x(),
              worksAt.getPosition().y(),
              livestAt.getPosition().x(),
              livestAt.getPosition().y());
      if (distance >= 6) {
        return -3;
      } else if (distance <= 3) {
        return 5;
      }
    }
    return -5;
  }
}
