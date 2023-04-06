package org.elte.utils;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;

public abstract class ResourceLoader {
		public static InputStream loadResource(String resName) {
				return ResourceLoader.class.getClassLoader().getResourceAsStream(resName);
		}

		@SneakyThrows
		public static Image loadImage(String resName) {
				URL url = ResourceLoader.class.getClassLoader().getResource(resName);
				return ImageIO.read(url);
		}
}
