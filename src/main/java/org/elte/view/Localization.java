package org.elte.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Localization {

		private static Localization instance = null;
		private static Properties prop = null;

		private Localization() {

				try {
						InputStream input =
										new FileInputStream(
														"./src/main/resources/localization/localization_"
																		+ MainFrame.language
																		+ ".properties");
						prop = new Properties();
						prop.load(input);

				} catch (IOException ex) {
						ex.printStackTrace();
				}
		}

		public static Properties getProp() {
				if (instance == null) {
						instance = new Localization();
				}
				return prop;
		}

		public static void reloadProp() {
				instance = new Localization();
		}
}
