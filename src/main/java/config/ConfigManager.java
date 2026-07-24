package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static final Properties PROPERTIES = new Properties();

	static {
		load("config/config.properties");
		load("config/local.properties");
	}

	private ConfigManager() {

	}

	/**
	 * This method will load the config file
	 * @param fileName 
	 */
	private static void load(String fileName) {

		try {
			InputStream stream = ConfigManager.class.getClassLoader().getResourceAsStream(fileName);
			if (stream != null) {
				PROPERTIES.load(stream);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Cannot load the config file");
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = PROPERTIES.getProperty(key);
		return value;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return Integer.parseInt(get(key));
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(get(key));
	}
}
