package helpers;

public class CalculatorUtils {
	
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

}
