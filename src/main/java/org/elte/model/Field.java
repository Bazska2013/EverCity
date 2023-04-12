package org.elte.model;

import lombok.Getter;
import org.elte.model.building.Building;
import org.elte.model.building.Empty;
import org.elte.model.enums.FieldTypes;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Zone;
import org.elte.utils.ImageResource;

import java.awt.*;

@Getter
public class Field {
	Building building;
	private Zone zone;
	int x;
	int y;

	public Field(int x, int y) {
		// zone = üres zone;
		building = new Empty(); // Alapból üres building
		this.x = x;
		this.y = y;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public FieldTypes getZoneType() {
		if (zone instanceof org.elte.model.zone.Empty) return FieldTypes.EMPTY;
		else if (zone instanceof Industry) return FieldTypes.INDUSTRY;
		else if (zone instanceof Resident) return FieldTypes.RESIDENT;
		else return FieldTypes.SERVICE;
	}

	public void drawField(Graphics g, boolean zoneMode) {
		if(zoneMode) {
			g.drawImage(
					getZoneImageResource(),
					getX(),
					getY(),
					Game.getBoxWidth(),
					Game.getBoxheight(),
					null);
		} else {
			g.drawImage(
					getImageResource(),
					getX(),
					getY(),
					Game.getBoxWidth(),
					Game.getBoxheight(),
					null);
		}
	}

	private Image getZoneImageResource() {

		return switch (getZoneType()) {
			case EMPTY -> ImageResource.ZONE_EMPTY;
			case SERVICE -> ImageResource.ZONE_SERVICE;
			case INDUSTRY -> ImageResource.ZONE_INDUSTRY;
			case RESIDENT -> ImageResource.ZONE_RESIDENT;
			default -> null;
		};
	}

	private Image getImageResource() {

		return switch (building.getBuildingType()) {
			case FACTORY -> ImageResource.FACTORY;
			case EMPTY -> ImageResource.GRASS;
			case ROAD -> ImageResource.ROAD;
			case FOREST -> ImageResource.FOREST;
			case POLICE -> ImageResource.POLICE;
			case HOUSE_1 -> ImageResource.HOUSE;
			case HOUSE_2 -> ImageResource.HOUSE_LV2;
			case HOUSE_3 -> ImageResource.HOUSE_LV3;
			case STADIUM -> ImageResource.STADIUM;
			case FIRE_STATION -> ImageResource.FIRE_STATION;
			default -> null;
		};

	}

}
