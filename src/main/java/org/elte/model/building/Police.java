package org.elte.model.building;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.utils.ImageResource;
import org.elte.utils.Position;

public class Police extends Building {
		public Police(Position position) {
				this.position = position;
				buildingType = BuildingTypes.POLICE;
				price = 30;
				yearlyFee = 10;
				socialImpact = 5;
				impactRange = 30;
				width = 1;
				height = 1;
				chanceToFire = 1;
				burning = false;
				imageResource = ImageResource.POLICE;
		}

		public boolean isPlaceable() {
				Game game = Game.getInstance();
				Field currentField = game.getField(position);

				return hasEnoughSpace() && currentField.getZone().isEmpty() && hasRoadAtLeastOneSide();
		}

		public boolean isConnected() {
				return true;
		}

		public boolean upgradeable() {
				return true;
		}

		public boolean isBurning() {
				return false;
		}

		public boolean isEmpty() {
				return false;
		}

		public int getTax() {
				return yearlyFee;
		}
}
