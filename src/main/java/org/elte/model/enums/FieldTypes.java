package org.elte.model.enums;

import java.io.Serializable;

/**
 * Enum osztály, amely a zóna típusokat tartalmazza. Az enum értékei az egyes mezők típusait
 * reprezentálják.
 */
public enum FieldTypes implements Serializable {
  ROAD,
  EMPTY,
  RESIDENT,
  INDUSTRY,
  SERVICE;
}
