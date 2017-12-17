import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import org.apache.commons.lang3.ArrayUtils;

public class Calculator {

	private static final String DELIMETER = "\\((.*)\\)";
	private static final String DELIMETER2 = "[(\\,\\)|)]";

	private static boolean isAppStarted = false;

	private static Stack<Float> stack = new Stack<Float>();

	public static boolean isNum(String str) {
		boolean ret = true;
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException e) {
			ret = false;
		}
		return ret;
	}

	public static Float convertReturn(String str) {
		return Float.valueOf(str);
	}

	public static String[] something(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if ("".equals(arr[i])) {
				arr[i] = null;
			}
		}

		return arr;
	}

	public static void calculate(String arg) throws Exception {
		// add(8,subs(16,mult(3,4)))
		// div(add(2,3), subs(mult(3,4),add(2,2)))

		Float result = 1f;

		String[] array = arg.split(DELIMETER2);
		array = something(array);
		ArrayUtils.reverse(array);

		List<Object> storesNumber = new ArrayList<Object>();

		for (String token : array) {

			if (null != token) {
				if (isNum(token)) {
					storesNumber.add(token);
				} else {
					int size = storesNumber.size();
					String numberOne = storesNumber.get(size - 2).toString();
					String numberTwo = storesNumber.get(size - 1).toString();
					switch (token) {
					case "add":
						storesNumber.set(size - 2, convertReturn(numberOne) + convertReturn(numberTwo));
						storesNumber.remove(size - 1);
						break;
					case "subs":
						storesNumber.set(size - 2, convertReturn(numberTwo) - convertReturn(numberOne));
						storesNumber.remove(size - 1);
						break;
					case "mult":
						storesNumber.set(size - 2, convertReturn(storesNumber.get(size - 2).toString())
								* convertReturn(storesNumber.get(size - 1).toString()));
						storesNumber.remove(size - 1);
						break;
					case "div":
						storesNumber.set(size - 2, convertReturn(numberTwo) / convertReturn(numberOne));
						storesNumber.remove(size - 1);
						break;
					default:
						throw new Exception("Invalid arguement");
					}
				}
			}
		}
		isAppStarted = true;
		System.out.println("Result: " + storesNumber.get(0));
	}

	public static void main(String[] args) {
		try {
			Scanner reader = new Scanner(System.in);
			String input;

			do {
				System.out.println(
						isAppStarted ? "\nI also accept more inputs...\nType end if you'd like to terminate me."
								: "Write your statement.\nE.g.: div(add(2,3),subs(mult(3,4),add(2,2)))");
				input = reader.nextLine();
				calculate(input.toLowerCase().replace(" ", ""));
			} while (!"end".equals(input));
			System.out.println("PROGRAM TERMINATED");
		} catch (Exception e) {
			System.out.println("Syntax error. Please check your input.");
		}
	}
}
