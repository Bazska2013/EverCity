package org.elte.model.zone;

import java.util.ArrayList;
import org.elte.model.enums.FieldTypes;
import org.elte.model.people.Citizen;

import java.util.List;
import org.elte.model.people.Citizen;
import org.elte.view.MainFrame;

public class Resident extends Zone {

		protected List<Citizen> citizens = new ArrayList<>();

		public Resident(int capacity, int hapiness, int tax, int price, int level) {
				super(price);
				this.capacity = capacity;
				this.hapiness = hapiness;
				this.tax = tax;
				this.level = level;

				if (MainFrame.startingCitizensToAdd > 0) {
						for (int i = 0; i < Math.min(MainFrame.startingCitizensToAdd, capacity); i++) {
								citizens.add(new Citizen());
								MainFrame.getInstance().addCitizen();
						}
						this.capacity -= citizens.size();
						MainFrame.startingCitizensToAdd -= citizens.size();
				}
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
				return FieldTypes.RESIDENT;
		}
        @Override
        public int getTax() {
            return tax*capacity;
        }
}
