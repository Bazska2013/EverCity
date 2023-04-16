package org.elte.utils;

import org.elte.model.building.*;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Service;

import java.awt.*;

public abstract class ImageResource {

		public static final Image GRASS = ResourceLoader.loadImage("grass.png");
		public static final Image FACTORY = ResourceLoader.loadImage("factory.png");
		public static final Image FIRETRUCK = ResourceLoader.loadImage("firetruck.png");
		public static final Image FIRE_STATION = ResourceLoader.loadImage("firestation.png");
		public static final Image STADIUM = ResourceLoader.loadImage("football-stadion.png");
		public static final Image FOREST = ResourceLoader.loadImage("forest.png");
		public static final Image HOUSE = ResourceLoader.loadImage("house.png");
		public static final Image HOUSE_LV2 = ResourceLoader.loadImage("house-v2.png");
		public static final Image HOUSE_LV3 = ResourceLoader.loadImage("house-v3.png");
		public static final Image POLICE = ResourceLoader.loadImage("police.png");
		public static final Image ROAD = ResourceLoader.loadImage("empty-road.png");

		public static final Image ZONE_RESIDENT = ResourceLoader.loadImage("zone_resident.png");
		public static final Image ZONE_INDUSTRY = ResourceLoader.loadImage("zone_industry.png");
		public static final Image ZONE_SERVICE = ResourceLoader.loadImage("zone_service.png");
		public static final Image ZONE_EMPTY = ResourceLoader.loadImage("zone_empty.png");
		public static final Image ZONE_MODE_TOGGLE = ResourceLoader.loadImage("zone_mode_toggle.png");

		public static Image getImageOfClass(Class<?> clazz) {
				if (clazz == FireStation.class) return FIRE_STATION;
				if (clazz == Factory.class) return FACTORY;
				if (clazz == Stadium.class) return STADIUM;
				if (clazz == Forest.class) return FOREST;
				if (clazz == Police.class) return POLICE;
				if (clazz == Road.class) return ROAD;
				if (clazz == House.class) return HOUSE;
				return GRASS;
		}

		public static Image getZoneImageOfClass(Class<?> clazz) {
				if (clazz == Industry.class) return ZONE_INDUSTRY;
				if (clazz == Resident.class) return ZONE_RESIDENT;
				if (clazz == Service.class) return ZONE_SERVICE;
				return ZONE_EMPTY;
		}
}
