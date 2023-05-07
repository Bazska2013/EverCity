package org.elte.model.zone;

import lombok.Getter;
import lombok.Setter;
import org.elte.model.enums.FieldTypes;
import org.elte.view.MainFrame;

@Getter
@Setter
public abstract class Zone {

		public Zone(int price) {
				this.price = price;
		}

		protected int price;
		protected int capacity;
		protected int hapiness;
		protected int tax;
		protected int level;

		public abstract boolean isDeletable();

		public abstract boolean isEmpty();

		public abstract FieldTypes getZoneType();

        public abstract int getTax();

		public void cost(){
			MainFrame.SetBalance(MainFrame.GetBalance()-price);
		}

}
