package helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;

public class CalculatorFile {

	private static final String DOC_NAME = "map";
	private static final String DOC_EXTENSION = ".txt";
	private static final String DOC_PATH = ""; // no need to set path. let the file be stored in the project folder.

	public static void saveToFile(Map<String, List<Object>> map) throws Exception {
		if(map.size() > 0) {
			Properties properties = new Properties();

			for (Map.Entry<String, List<Object>> entry : map.entrySet()) {
				String jsonList = new Gson().toJson(entry.getValue());
				properties.put(entry.getKey(), jsonList);
			}
			
			properties.store(new FileOutputStream(DOC_NAME + DOC_EXTENSION), null);
		} else {
			throw new Exception("You haven't saved any variable in memory.");
		}
	}

	public static Map<String, List<Object>> loadFile() throws IOException {
		Map<String, List<Object>> loadedVariables = new HashMap<String, List<Object>>();
		BufferedReader reader;

		reader = new BufferedReader(new FileReader("map.txt"));

			String line = reader.readLine();
			while (line != null) {

				if (!line.startsWith("#")) {
					String variableName = line.substring(0, line.indexOf("="));
					
					String listCalculation = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
					List<Object> variableDefinition = deserializeList(listCalculation);
					
					loadedVariables.put(variableName, variableDefinition);
					
				}

				line = reader.readLine();
			}
			reader.close();
		
		return loadedVariables;
	}

	private static List<Object> deserializeList(String stringList) {

		List<Object> list = new ArrayList<Object>();

		String indexZero = stringList.substring(stringList.indexOf("\"") + 1, stringList.indexOf("\","));
		list.add(indexZero);
		String indexOne = stringList.substring(stringList.indexOf("\",") + 2);
		list.add(indexOne);

		return list;
	}
	
}
