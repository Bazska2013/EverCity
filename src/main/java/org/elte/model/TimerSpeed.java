package org.elte.model;
/** Az időzítő sebességét reprezentáló felsorolás típus. */
public enum TimerSpeed {
  STOP(-1),
  ONE(1000),
  TWO(50);
  /** Az idő sebessége. */
  final int amount;
  /**
   * Konstruktor a TimerSpeed objektumok létrehozásához.
   *
   * @param amount az időzítő sebessége
   */
  TimerSpeed(int amount) {
    this.amount = amount;
  }
  /**
   * Visszaadja az időzítő sebességét.
   *
   * @return az időzítő sebessége
   */
  public int getValue() {
    return this.amount;
  }
  /**
   * Visszaadja a TimerSpeed objektumot a megadott késleltetési érték alapján.
   *
   * @param delay a késleltetési érték
   * @return a megfelelő TimerSpeed objektum, vagy null, ha nincs egyezés
   */
  public static TimerSpeed valueOfDelay(Integer delay) {
    for (TimerSpeed e : values()) {
      if (e.amount == delay) {
        return e;
      }
    }
    return null;
  }
}
