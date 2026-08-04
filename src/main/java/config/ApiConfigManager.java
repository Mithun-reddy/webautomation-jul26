package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfigManager {

	private static final Properties PROPERTIES = new Properties();
	private static final String ENVIRONMENT = System.getProperty("env", "qa");
	
	
	private static void load() {

		String filePath = "config/"+ENVIRONMENT+".properties";
		try {
			InputStream stream = ConfigManager.class.getClassLoader().getResourceAsStream(filePath);
			if (stream != null) {
				PROPERTIES.load(stream);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Cannot load the config file");
		}
	}
	
	public static String get(String key) {
		String value = PROPERTIES.getProperty(key);
		return value;
	}
}
