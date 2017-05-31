package ar.edu.frc.utn.avads.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author gustavo
 *
 */
public class PropertiesClientUtil {

	final static Logger log = Logger.getLogger(PropertiesClientUtil.class.getName());
	
	private static Properties properties;

	public static void cargarPropiedades() {

		log.info("loading properties... ");
		InputStream inputC = PropertiesClientUtil.class
				.getResourceAsStream("/ApplicationResources_es.properties");
		try {
			properties = new Properties();
			properties.load(inputC);
			inputC.close();
		} catch (IOException ex) {
			Logger.getLogger(PropertiesClientUtil.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public static String getProperty(String key) {
		String value = "";

		if (properties != null) {
			value = properties.getProperty(key);
		} else {
			cargarPropiedades();
                        value = properties.getProperty(key);
		}

		return value;
	}
        
	public static String setProperty(String key, String value) {

		if (properties != null) {
			properties.setProperty(key, value);
		} else {
			cargarPropiedades();
                        properties.setProperty(key, value);
		}
                
                OutputStream output = null;
                try {
                    output = new FileOutputStream("/ApplicationResources_es.properties");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PropertiesClientUtil.class.getName()).log(Level.SEVERE, null, ex);
                }        
                try {
                    properties.store(output, null);
                    output.close();        
                } catch (IOException ex) {
                    Logger.getLogger(PropertiesClientUtil.class.getName()).log(Level.SEVERE, null, ex);
                }                

		return value;
	}        

}
