import java.util.Scanner;
import services.CalculatorServices;

public class Calculator extends CalculatorServices {


//	private static CalculatorServices services;


	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {

		// add(8,subs(16,mult(3,4)))
		// div(add(2,3), subs(mult(3,4),add(2,2)))
		// put(a,add(8,subs(16,mult(3,4))))

		try {
			@SuppressWarnings("resource")
			Scanner reader = new Scanner(System.in);
			String input;

			String firstMessage = "\n\nWhat is your decision?\nE.g.1: put(a,add(3,3))\nE.g.2: get(a)\nE.g.3: remove(a)\nE.g.4: print\n\nType save to save variables.\nType load to load previous variables.";
			String secondMessage = "\n\nYou may declare more variables with correct statement.";

			do {
				System.out.println(isAppStarted ? secondMessage : firstMessage);
				input = reader.nextLine();

				decisionFromInput(input.toLowerCase().replace(" ", ""));

			} while (!input.equals("end") || !input.equals("exit"));
			System.out.println("Warning: PROGRAM TERMINATED");
		} catch (IndexOutOfBoundsException | ClassCastException c) {
			System.err.println("Exception: Invalid argument...");
		}
	}
}
