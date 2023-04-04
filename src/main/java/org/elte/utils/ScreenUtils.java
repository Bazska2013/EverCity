package org.elte.utils;

import java.awt.*;

public abstract class ScreenUtils {
		public static Dimension getScreenSize() {
				Toolkit tk = Toolkit.getDefaultToolkit();
				return tk.getScreenSize();
		}
}
