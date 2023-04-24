package org.elte.model.zone;

import org.elte.model.enums.FieldTypes;

public class Service extends Zone {

		public Service(int capacity, int hapiness, int tax, int price, int level) {
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

		@Override
		public FieldTypes getZoneType() {
				return FieldTypes.SERVICE;
		}
        @Override
        public int getTax() {
            return tax*capacity;
        }
}
