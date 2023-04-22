package org.elte.model.people;

import lombok.Getter;
import lombok.Setter;
import org.elte.model.zone.Zone;

@Getter
@Setter
public class Citizen {
		private Zone livestAt;
		private Zone worksAt;
		private int satisfaction;
		private int income;
		private int age;
		private boolean isRetired = false;

		public Citizen(Zone livesAt, Zone worksAt, int age) {
			this.livestAt = livesAt;
			this.worksAt = worksAt;
			this.age = age;
			satisfaction = 50;
			income = 0;
		}

		public void resetCitizen() {
				System.out.println("Citizen resetted...");
				satisfaction = 50;
				income = 0;
				age = 18;
				isRetired = false;
		}

}
