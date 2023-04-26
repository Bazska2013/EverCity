package org.elte.model.building;

public class House extends Building {

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

		public int getTax() {
				return yearlyFee;
		}
}
