package org.elte.model.people;

import org.elte.model.zone.Zone;

public class Citizen {
		private Zone livestAt;
		private Zone worksAt;
		private int hapiness;
		private int income;
		private int age;

		private void resetCitizen() {
				livestAt = null;
				worksAt = null;
				hapiness = 50;
				income = 0;
				age = 18;
		}
		;

		public Zone getLivestAt() {
				return livestAt;
		}

		public void setLivestAt(Zone livestAt) {
				this.livestAt = livestAt;
		}

		public Zone getWorksAt() {
				return worksAt;
		}

		public void setWorksAt(Zone worksAt) {
				this.worksAt = worksAt;
		}

		public int getHapiness() {
				return hapiness;
		}

		public void setHapiness(int hapiness) {
				this.hapiness = hapiness;
		}

		public int getIncome() {
				return income;
		}

		public void setIncome(int income) {
				this.income = income;
		}

		public int getAge() {
				return age;
		}

		public void setAge(int age) {
				this.age = age;
		}
}
