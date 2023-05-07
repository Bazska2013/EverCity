package org.elte.model;

public enum TimerSpeed {
		STOP(-1),
		ONE(1000),
		TWO(500);

		final int amount;

		TimerSpeed(int amount) {
				this.amount = amount;
		}

		public int getValue() {
				return this.amount;
		}

	public static TimerSpeed valueOfDelay(Integer delay) {
		for (TimerSpeed e : values()) {
			if (e.amount == delay) {
				return e;
			}
		}
		return null;
	}
}
