package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import helpers.CalculatorFile;
import helpers.CalculatorUtils;
import models.Decision;

public class CalculatorServices {
	private static final String DELIMETER = "[(\\,\\)|)]";
	
	private static CalculatorUtils utils;
	private static CalculatorFile file;
	
	protected static Map<String, List<Object>> map = new HashMap<String, List<Object>>();
	protected static boolean isAppStarted = false;
	
	@SuppressWarnings("static-access")
	public static void decisionFromInput(String arg) throws Exception, IndexOutOfBoundsException {
		if (arg.equals(Decision.load.getPrefix())) {
			map = file.loadFile();
			System.out.println("Variables are loaded into program.");
		} else if (arg.equals(Decision.save.getPrefix())) {
			file.saveToFile(map);
			System.out.println("The map has been saved to file.");
		} else if (arg.startsWith(Decision.get.getPrefix())) {
			// gets the value if the variable exists in the map.

			String variableName = arg.substring(arg.indexOf("(") + 1, arg.indexOf(")"));

			List<?> helper = map.get(variableName);
			if (null != helper) {
				System.out.println("Value: " + helper.get(1));
			} else {
				System.err.println("Error: You haven't declared any variable named " + variableName);
			}

		} else if (arg.startsWith(Decision.put.getPrefix())) {
			// sets a value to a variable.

			List<Object> storeStatementAndValue = new ArrayList<Object>();

			String variableName = arg.substring(arg.indexOf("(") + 1, arg.indexOf(","));
			if (null == map.get(variableName)) {

				// length - 1 to remove last ) which belongs to main operation.
				// put( //)//
				String statement = arg.substring(arg.indexOf(",") + 1, arg.length() - 1);
				Float result = calculate(statement);

				storeStatementAndValue.add(statement);
				storeStatementAndValue.add(result);

				map.put(variableName, storeStatementAndValue);
				System.out.println("Variable '" + variableName + "' has been declared.\nType get(" + variableName
						+ ") to see its value.");

			} else {
				System.err.println("Error: Variable '" + variableName + "' exists in the map.");
			}

		} else if (arg.startsWith(Decision.print.getPrefix())) {
			// prints all operations

			if (map.size() > 0) {
				for (String key : map.keySet()) {
					System.out.println("Variable name: " + key);
					List<?> helper = map.get(key);
					System.out.println("\tStatement: " + helper.get(0));
					System.out.println("\tResult: " + helper.get(1));
					System.out.println("------------------\n");
				}
			} else {
				System.err.println("Error: You must declare some variables first in order to show them all.");
			}
		} else if (arg.startsWith(Decision.remove.getPrefix())) {
			// removes the variable if it exists in the map.

			String variableName = arg.substring(arg.indexOf("(") + 1, arg.indexOf(")"));

			if (null != map.get(variableName)) {
				map.remove(variableName);
				System.out.println("Variable name '" + variableName + "' has been removed from the map.");
			} else {
				System.err.println("Error: No variable named '" + variableName + "' found.");
			}
		} else {
			Float result = calculate(arg);
			System.out.println("Unsaved result: " + result);
		}
	}

	@SuppressWarnings("static-access")
	public static Float calculate(String arg) throws Exception, IndexOutOfBoundsException, ClassCastException {

		String[] array = arg.split(DELIMETER);
		array = utils.setEmptyToNullInArray(array);
		ArrayUtils.reverse(array);

		List<Object> storesNumber = new ArrayList<Object>();

		for (String token : array) {
			if (null != token) {
				if (utils.isNum(token) || (token.length() == 1 && Character.isLetter(token.charAt(0)))) {
					if (Character.isLetter(token.charAt(0))) {
						if (null != map.get(token)) {
							storesNumber.add(map.get(token).get(1));
						} else {
							throw new Exception("Variable " + token + " does not exist...");
						}
					} else {
						storesNumber.add(token);
					}
				} else {
					int size = storesNumber.size();
					String numberOne = storesNumber.get(size - 2).toString();
					String numberTwo = storesNumber.get(size - 1).toString();
					switch (token) {
					case "add":
						storesNumber.set(size - 2, utils.convertStringToFloat(numberOne) + utils.convertStringToFloat(numberTwo));
						storesNumber.remove(size - 1);
						break;
					case "subs":
						storesNumber.set(size - 2, utils.convertStringToFloat(numberTwo) - utils.convertStringToFloat(numberOne));
						storesNumber.remove(size - 1);
						break;
					case "mult":
						storesNumber.set(size - 2, utils.convertStringToFloat(storesNumber.get(size - 2).toString())
								* utils.convertStringToFloat(storesNumber.get(size - 1).toString()));
						storesNumber.remove(size - 1);
						break;
					case "div":
						storesNumber.set(size - 2, utils.convertStringToFloat(numberTwo) / utils.convertStringToFloat(numberOne));
						storesNumber.remove(size - 1);
						break;
					default:
						throw new Exception("Exception: Invalid argument");
					}
				}
			}
		}
		isAppStarted = true;

		return (float) storesNumber.get(0);
	}

}
