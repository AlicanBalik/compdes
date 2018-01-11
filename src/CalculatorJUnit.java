import static org.junit.Assert.*;

import org.junit.Test;

import helpers.CalculatorUtils;

public class CalculatorJUnit {

	/*********************************************
	 * Testing decisionFromInput(String arg) PUT operation
	 * 
	 * @throws Exception
	 * @throws IndexOutOfBoundsException
	 ********************************************/

	@Test(expected = ClassCastException.class)
	public void PUTdecisionFromInputWithoutVariableNameShouldThrowException()
			throws IndexOutOfBoundsException, Exception {
		Calculator.decisionFromInput("put(add(,3,");
	}
	
	@Test(expected = Exception.class)
	public void PUTdecisionFromInputShouldThrowException()
			throws IndexOutOfBoundsException, Exception {
		Calculator.decisionFromInput("put(add(m,,+,/,35f,3))");
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void PUTdecisionFromInputWithVariableNameShouldThrowException() throws IndexOutOfBoundsException, Exception {
		// missing ) of put()
		Calculator.decisionFromInput("put(a,add(mult(3,5))");
	}

	@Test
	public void PUTdecisionShouldNotThrowException() throws IndexOutOfBoundsException, Exception {
		Calculator.decisionFromInput("put(a,add(5,5))");
	}

	/*********************************************
	 * Testing calculate(String arg)
	 * 
	 * @throws Exception
	 * @throws IndexOutOfBoundsException
	 ********************************************/

	@Test(expected = Exception.class)
	public void calculateWithoutNumbersShouldThrowException() throws IndexOutOfBoundsException, Exception {
		Calculator.calculate("add(,,)");
	}

	@Test(expected = Exception.class)
	public void calculateWithLettersShouldThrowException() throws IndexOutOfBoundsException, Exception {
		Calculator.calculate("add(a,3.5)");
	}

	@Test(expected = Exception.class)
	public void calculateWithWrongSyntaxShouldThrowException() throws IndexOutOfBoundsException, Exception {
		Calculator.calculate("div(add(2,3), subs(mult(3,4),add(2,2)");
	}

	@Test(expected = Exception.class)
	public void calculateWithShouldNotThrowException() throws IndexOutOfBoundsException, Exception {
		Calculator.calculate("add(3,3)");
		Calculator.calculate("add(add(mult(3,div(15,5)), subs(100,50), 15))");
	}

	/*****************
	 * Testing isNum(String str)
	 ****************/
	@Test
	public void isNumShouldReturnFalse() {
		assertEquals(false, CalculatorUtils.isNum("a"));
	}

	@Test
	public void isNumShouldReturnTrue() {
		assertEquals(true, CalculatorUtils.isNum("99.99"));
	}

	/********************************
	 * Testing convertStringToFloat(String str)
	 *******************************/

	@Test()
	public void convertStringToFloatShouldReturnFloatNumber() {
		assertEquals(new Float("0.34"), CalculatorUtils.convertStringToFloat("0.34"));
	}

	@Test(expected = NumberFormatException.class)
	public void convertStringToFloatShouldThrowException() {
		CalculatorUtils.convertStringToFloat("a");
	}
}
