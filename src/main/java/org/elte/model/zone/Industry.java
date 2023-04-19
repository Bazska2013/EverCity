package org.elte.model.zone;

public class Industry extends Zone {

		public Industry(int capacity, int hapiness, int tax, int price, int level) {
				super(price);
				this.capacity = capacity;
				this.hapiness = hapiness;
				this.tax = tax;
				this.level = level;
		}

		@Override
		public boolean isDeletable() {
				return false;
		}

		@Override
		public boolean isEmpty() {
				return false;
		}
}
