package ar.edu.frc.utn.avads.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author gustavo
 *
 */
public class PropertiesUtil {

	final static Logger log = Logger.getLogger(PropertiesUtil.class.getName());
	
	private static Properties properties;

	public static void cargarPropiedades() {

		log.info("loading properties... ");
		InputStream inputC = PropertiesUtil.class
				.getResourceAsStream("/avads.properties");
		try {
			properties = new Properties();
			properties.load(inputC);
			inputC.close();
		} catch (IOException ex) {
			Logger.getLogger(PropertiesUtil.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public static String getProperty(String key) {
		String value = "";

		if (properties != null) {
			value = properties.getProperty(key);
		} else {
			cargarPropiedades();
		}

		return value;
	}

}
