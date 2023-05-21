package org.elte.model.people;

import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Zone;
import org.elte.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CitizenTest {
  private Zone livesAt;
  private Zone worksAt;
  private Citizen citizen;

  @Before
  public void setUp() {
    livesAt = new Resident(new Position(0, 0));
    worksAt = new Industry(new Position(0, 2));
    citizen = new Citizen(livesAt, worksAt, 40);
  }

  @Test
  public void testResetCitizen() {
    citizen.resetCitizen();

    assertEquals(50, citizen.getSatisfaction());
    assertEquals(0, citizen.getIncome());
    assertEquals(18, citizen.getAge());
    assertFalse(citizen.isRetired());
  }

  @Test
  public void testIsRetired() {
    assertFalse(citizen.isRetired());

    citizen.below65();

    assertFalse(citizen.isRetired());

    citizen.setAge(66);
    citizen.below65();

    assertTrue(citizen.isRetired());
  }
}
