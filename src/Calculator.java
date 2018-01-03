import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.lang3.ArrayUtils;

import models.Decision;

public class Calculator {

	private static final String DELIMETER = "[(\\,\\)|)]";

	private static boolean isAppStarted = false;
	private static Map<String, List<?>> map = new HashMap<String, List<?>>();

	public static boolean isNum(String str) {
		boolean ret = true;
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException e) {
			ret = false;
		}
		return ret;
	}

	public static Float convertStringToFloat(String str) {
		return Float.valueOf(str);
	}

	public static String[] setEmptyToNullInArray(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if ("".equals(arr[i])) {
				arr[i] = null;
			}
		}

		return arr;
	}

	public static void decisionFromInput(String arg) throws Exception, IndexOutOfBoundsException {

		if (arg.startsWith(Decision.get.getPrefix())) {
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

	public static Float calculate(String arg) throws Exception, IndexOutOfBoundsException, ClassCastException {

		String[] array = arg.split(DELIMETER);
		array = setEmptyToNullInArray(array);
		ArrayUtils.reverse(array);

		List<Object> storesNumber = new ArrayList<Object>();

		for (String token : array) {
			if (null != token) {
				if (isNum(token) || (token.length() == 1 && Character.isLetter(token.charAt(0)))) {
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
						storesNumber.set(size - 2, convertStringToFloat(numberOne) + convertStringToFloat(numberTwo));
						storesNumber.remove(size - 1);
						break;
					case "subs":
						storesNumber.set(size - 2, convertStringToFloat(numberTwo) - convertStringToFloat(numberOne));
						storesNumber.remove(size - 1);
						break;
					case "mult":
						storesNumber.set(size - 2, convertStringToFloat(storesNumber.get(size - 2).toString())
								* convertStringToFloat(storesNumber.get(size - 1).toString()));
						storesNumber.remove(size - 1);
						break;
					case "div":
						storesNumber.set(size - 2, convertStringToFloat(numberTwo) / convertStringToFloat(numberOne));
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

	public static void main(String[] args) throws Exception {

		// add(8,subs(16,mult(3,4)))
		// div(add(2,3), subs(mult(3,4),add(2,2)))
		// put(a,add(8,subs(16,mult(3,4))))

		try {
			@SuppressWarnings("resource")
			Scanner reader = new Scanner(System.in);
			String input;

			String firstMessage = "What is your decision?\nE.g.1: put(a,add(3,3))\nE.g.2: get(a)\nE.g.3: remove(a)\nE.g.4: print";
			String secondMessage = "\n\nYou may declare more variables with correct statement.";

			do {
				System.out.println(isAppStarted ? secondMessage : firstMessage);
				input = reader.nextLine();

				decisionFromInput(input.toLowerCase().replace(" ", ""));

			} while (!"end".equals(input));
			System.out.println("Warning: PROGRAM TERMINATED");
		} catch (IndexOutOfBoundsException | ClassCastException c) {
			System.err.println("Exception: Invalid argument...");
		}
	}
}
