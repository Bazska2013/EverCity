package org.elte.model.building;

public class House extends Building {
		@Override
		public boolean isPlaceable() {
				return false;
		}

		@Override
		public boolean isDeletable() {
				return false;
		}

		@Override
		public boolean isConnected() {
				return false;
		}

		@Override
		public boolean upgradeable() {
				return false;
		}

		@Override
		public boolean isBurning() {
				return false;
		}

		@Override
		public boolean isEmpty() {
				return false;
		}

		public void disableDeletable() {}

		public void removeDeletable() {}
}
