package helpers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class CalculatorProperties {

	public static void loadProperties() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "messages.properties";
			input = CalculatorProperties.class.getResourceAsStream(filename);
			if (null == input) {
				throw new Exception("Configuration error occured. Unable to run the program...");
			}

			// load a properties file from class path, inside static method
			// we set everything static LoL
			prop.load(input);
			// **** we would use below line if the function wasn't static
			// prop.load(getClass().getClassLoader().getResourceAsStream("messages.properties"));

			// get the property value and print it out
			System.out.println(prop.getProperty("alican"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

//	public void saveProperties() throws Exception {
//
//		Properties prop = new Properties();
//		OutputStream output = null;
//
//		try {
//
//			output = new FileOutputStream("messages.properties");
//
//			// set the properties value
//			prop.setProperty("alican", "balik");
//
//			// save properties to project root folder
//			prop.store(output, null);
//
//		} catch (IOException io) {
//			io.printStackTrace();
//		} finally {
//			if (output != null) {
//				output.close();
//			}
//		}
//	}
}
