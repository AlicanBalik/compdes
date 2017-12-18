import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorJUnit {

	/*****************
	 * Testing isNum(String str)
	 ****************/
	@Test
	public void isNumShouldReturnFalse() {
		assertEquals(false, Calculator.isNum("a"));
	}

	@Test
	public void isNumShouldReturnTrue() {
		assertEquals(true, Calculator.isNum("99.99"));
	}

	/********************************
	 * Testing convertStringToFloat(String str)
	 *******************************/

	@Test()
	public void convertStringToFloatShouldReturnFloatNumber() {
		assertEquals(new Float("0.34"), Calculator.convertStringToFloat("0.34"));
	}

	@Test(expected = NumberFormatException.class)
	public void convertStringToFloatShouldThrowException() {
		Calculator.convertStringToFloat("a");
	}
	
	/*********************************************
	 * Testing decisionFromInput(String arg)
	 * @throws Exception 
	 * @throws IndexOutOfBoundsException 
	 ********************************************/
	
	@Test(expected = ClassCastException.class)
	public void decisionFromInputWithoutVariableNameShouldThrowException() throws IndexOutOfBoundsException, Exception {
		Calculator.decisionFromInput("put(add(,3,");
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void decisionFromInputWithVariableNameShouldThrowException() throws IndexOutOfBoundsException, Exception {
		Calculator.decisionFromInput("put(a,asdasd)");
	}
	
	/*********************************************
	 * Testing calculate(String arg)
	 * @throws Exception 
	 * @throws IndexOutOfBoundsException 
	 ********************************************/
	
}
