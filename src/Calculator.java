import java.util.Scanner;

import helpers.CalculatorProperties;
import services.CalculatorServices;

public class Calculator extends CalculatorServices {

	private static CalculatorProperties calculatorProperties;

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// calculatorProperties.loadProperties();

		/**********************************************
		* put(a,add(8,sub(16,mult(3,4))))
		* put(b,add(22,sub(16,mult(3,4))))
		* put(c,div(add(2,3), sub(mult(3,4),add(2,2))))
		
		* get(a)
		* move(add(b,c),a)
		* get(a)
		* remove(a)
		
		* print
		 *****************************************************/

		try (Scanner reader = new Scanner(System.in)) {
			@SuppressWarnings("resource")
			String input = "";

			String firstMessage = "\n\nWhat is your decision?\n" + "E.g.1: put(a,add(3,3))\n" + "E.g.2: get(a)\n"
					+ "E.g.3: move(mult(a,3),a)\n" + "E.g.4: remove(a)\n" + "E.g.5: print\n\n"
					+ "Type save to save variables in a file.\n" + "Type load to load previous variables from a file.";
			String secondMessage = "\n\nYou may keep declaring more variables with correct statements.";

			System.out.println(isAppStarted ? secondMessage : firstMessage);
			while (!input.equals("end") || !input.equals("exit")) {
				input = reader.nextLine();
				decisionFromInput(input.toLowerCase().replace(" ", ""));
			}
		} catch (IndexOutOfBoundsException | ClassCastException c) {
			System.err.println("Exception: Invalid argument...");
		} catch (Exception e) {
			System.err.print(e.getMessage());
		}
		System.out.println("Warning: PROGRAM TERMINATED");
	}
}
