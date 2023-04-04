package org.elte.view.common.buttons;

import java.awt.*;

public class TextButton extends CustomButton {
		public TextButton(String text) {
				super(text);
				this.setBackground(new Color(3, 193, 60));
				this.setForeground(Color.WHITE);
				this.setFocusPainted(false);
				this.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
}
