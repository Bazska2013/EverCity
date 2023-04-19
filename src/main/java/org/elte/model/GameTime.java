package org.elte.model;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

@Getter
@Setter
public class GameTime extends Timer {
		private int passedDays = 0;
		private static final int START_YEAR = 2023;

		public GameTime(ActionListener listener) {
				super(TimerSpeed.ONE.getValue(), listener);
				super.addActionListener(e -> passedDays++);
				super.setRepeats(true);
				super.start();
		}

		@Override
		public String toString() {
				return LocalDate.of(START_YEAR, 1, 1).plusDays(getPassedDays()).toString();
		}

		public void setTimerSpeed(TimerSpeed timerSpeed) {
				if (timerSpeed.getValue() < 0) {
						this.stop();
						return;
				}
				if (!this.isRunning()) {
						this.restart();
				}
				this.setDelay(timerSpeed.getValue());
		}

		public boolean overAYear(){
			if((getPassedDays()%365)==0){
				return true;
			}
			return false;
		}
}
