package org.elte.model.zone;

public class Empty extends Zone {

		public Empty(int price) {
				super(price);
				this.capacity = 0;
				this.hapiness = 0;
				this.tax = 0;
				this.level = 0;
		}

		@Override
		public boolean isDeletable() {
				return true;
		}

		@Override
		public boolean isEmpty() {
				return true;
		}
}
