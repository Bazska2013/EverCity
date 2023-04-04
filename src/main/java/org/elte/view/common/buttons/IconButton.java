package org.elte.view.common.buttons;

import java.awt.*;

public class IconButton extends CustomButton {

		public IconButton(String text) {
				super(text);
				reset();

		}

		public void reset() {
				this.setBorderPainted(false);
				this.setFocusPainted(false);
				this.setContentAreaFilled(true);
			this.setBackground(Color.WHITE);
		}

		public void check() {}
}
