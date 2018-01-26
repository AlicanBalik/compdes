package helpers;

public class CalculatorUtils {

	public static void exit(final int status) {
		// Thread practice.
		
		// When using multiple threading, if one of them executes System.exit(0),
		// it will be deadlock.
		// Deadlock occurs when 2 threads wait for each other.
		// Due of the fact that they unfortunately can't proceed.

		// How deadlock occurs when calling System.exit(0):
		// this method should not be called from an unknown thread. System.exit never
		// exits normally because the call will block until the JVM is terminated. It's
		// as if whatever code is running that has the power plug pulled on it before it
		// can finish. Calling System.exit will initiate the program's shutdown hooks
		// and whatever thread that calls System.exit will block until program
		// termination. This has the implication that if the shutdown hook in turn
		// submits a task to the thread from which System.exit was called, the program
		// will deadlock.
		
		// Below implementation handles this issue.
		// Long story short: 
		// We create a thread instance named App-exit which only handles program terminate.
		// By creating it, the system knows that terminate code is not called by an
		// unknown thread.
		new Thread("App-exit") {
			@Override
			public void run() {
				System.exit(status);
			}
		}.start();
	}

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
