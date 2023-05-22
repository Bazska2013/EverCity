package org.elte.utils;

import java.awt.*;

import org.elte.model.building.*;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Service;

/** Az ImageResource osztály kép erőforrásokat tartalmazó segédosztály. */
public abstract class ImageResource {

  public static final Image GRASS = ResourceLoader.loadImage("grass.png");
  public static final Image FACTORY = ResourceLoader.loadImage("factory_lvl1.png");

  public static final Image INDUSTRY_2 = ResourceLoader.loadImage("factory_lvl2.png");
  public static final Image INDUSTRY_3 = ResourceLoader.loadImage("factory_lvl3.png");
  public static final Image SERVICE_BUILDING =
      ResourceLoader.loadImage("service_building_lvl1.png");
  public static final Image SERVICE_2 = ResourceLoader.loadImage("service_building_lvl2.png");
  public static final Image SERVICE_3 = ResourceLoader.loadImage("service_building_lvl3.png");
  public static final Image FIRE_STATION = ResourceLoader.loadImage("firestation.png");
  public static final Image FIRETRUCK = ResourceLoader.loadImage("firetruck.png");
  public static final Image STADIUM = ResourceLoader.loadImage("stadium.png");
  public static final Image FOREST1 = ResourceLoader.loadImage("tree_lvl1.png");
  public static final Image FOREST2 = ResourceLoader.loadImage("tree_lvl2.png");
  public static final Image FOREST3 = ResourceLoader.loadImage("tree_lvl3.png");
  public static final Image FOREST4 = ResourceLoader.loadImage("tree_lvl4.png");
  public static final Image FOREST5 = ResourceLoader.loadImage("tree_lvl5.png");
  public static final Image FOREST6 = ResourceLoader.loadImage("tree_lvl6.png");
  public static final Image FOREST7 = ResourceLoader.loadImage("tree_lvl7.png");
  public static final Image FOREST8 = ResourceLoader.loadImage("tree_lvl8.png");
  public static final Image FOREST9 = ResourceLoader.loadImage("tree_lvl9.png");
  public static final Image FOREST10 = ResourceLoader.loadImage("tree_lvl10.png");
  public static final Image HOUSE = ResourceLoader.loadImage("house_lvl1.png");
  public static final Image HOUSE_LV2 = ResourceLoader.loadImage("house_lvl2.png");
  public static final Image HOUSE_LV3 = ResourceLoader.loadImage("house_lvl3.png");
  public static final Image POLICE = ResourceLoader.loadImage("police.png");
  public static final Image ROAD = ResourceLoader.loadImage("road.png");

  public static final Image ZONE_ROAD = ResourceLoader.loadImage("road.png");
  public static final Image ZONE_RESIDENT = ResourceLoader.loadImage("zone_resident.png");
  public static final Image ZONE_INDUSTRY = ResourceLoader.loadImage("zone_industry.png");
  public static final Image ZONE_SERVICE = ResourceLoader.loadImage("zone_service.png");
  public static final Image ZONE_EMPTY = ResourceLoader.loadImage("zone_empty.png");
  public static final Image ZONE_MODE_TOGGLE = ResourceLoader.loadImage("zone_mode_toggle.png");

  public static final Image FIRE = ResourceLoader.loadImage("fire.png");

  public static final Image RUBBLE = ResourceLoader.loadImage("rubble.png");
  /**
   * Visszaadja a megadott osztályhoz tartozó képet.
   *
   * @param clazz az osztály, amelyhez a kép tartozik
   * @return a kép erőforrás
   */
  public static Image getImageOfClass(Class<?> clazz) {
    if (clazz == FireStation.class) return FIRE_STATION;
    if (clazz == Factory.class) return FACTORY;
    if (clazz == ServiceBuilding.class) return SERVICE_BUILDING;
    if (clazz == Stadium.class) return STADIUM;
    if (clazz == Forest.class) return FOREST1;
    if (clazz == Police.class) return POLICE;
    if (clazz == Road.class) return ROAD;
    if (clazz == House.class) return HOUSE;
    if (clazz == Rubble.class) return RUBBLE;
    return GRASS;
  }
  /**
   * Visszaadja a megadott zóna osztályhoz tartozó képet.
   *
   * @param clazz a zóna osztály, amelyhez a kép tartozik
   * @return a zóna kép erőforrás
   */
  public static Image getZoneImageOfClass(Class<?> clazz) {
    if (clazz == org.elte.model.zone.Road.class) return ZONE_ROAD;
    if (clazz == Industry.class) return ZONE_INDUSTRY;
    if (clazz == Resident.class) return ZONE_RESIDENT;
    if (clazz == Service.class) return ZONE_SERVICE;
    return ZONE_EMPTY;
  }
}
