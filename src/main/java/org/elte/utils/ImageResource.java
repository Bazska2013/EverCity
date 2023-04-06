package org.elte.utils;

import org.elte.model.building.Factory;
import org.elte.model.building.Road;
import org.elte.model.building.Stadium;
import org.elte.model.building.Tree;

import java.awt.*;

public abstract class ImageResource {

		public static final Image GRASS = ResourceLoader.loadImage("grass.png");
		public static final Image FACTORY = ResourceLoader.loadImage("factory.png");
		public static final Image FIRETRUCK = ResourceLoader.loadImage("firetruck.png");
		public static final Image STADIUM = ResourceLoader.loadImage("football-stadion.png");
		public static final Image FOREST = ResourceLoader.loadImage("forest.png");
		public static final Image HOUSE = ResourceLoader.loadImage("house.png");
		public static final Image HOUSE_LV2 = ResourceLoader.loadImage("house-v2.png");
		public static final Image HOUSE_LV3 = ResourceLoader.loadImage("house-v3.png");
		public static final Image ROAD = ResourceLoader.loadImage("road.png");

		public static Image getImageOfClass(Class<?> clazz) {
				if (clazz == Factory.class) return FACTORY;
				if (clazz == Stadium.class) return STADIUM;
				if (clazz == Tree.class) return FOREST;
				if (clazz == Road.class) return ROAD;
				return GRASS;
		}
}
