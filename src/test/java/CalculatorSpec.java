import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Write a Function that parses the input String and returns the calculated output.
 * - input max: AD;5;10;20;30;40;60;70;80;90;1
 * - if correct, return 00
 * - if incorrect, return 01
 * - AD (ADD) & MU (MULTIPLY) functions
 * -->  example input: AD;5;5
 */

public class CalculatorSpec {

    @Test(expected = inputValidInputException.class)
    public void whenInputIsInvalidReturnException() throws inputValidInputException {
        Calculator c = new Calculator(10);
        String actual = c.calculate();
        String expected = "01";
        assertEquals(expected, actual);
    }

    @Test
    public void whenInputHasInvalidOperatorReturnTrue() {
        Calculator c = new Calculator("XX;1;2");
        String actual = c.calculate();
        String expected = "01";
        assertEquals(expected, actual);
    }

    @Test
    public void whenInputHasValidMUOperatorReturnTrue() {
        Calculator c = new Calculator("MU;1;2;3;4");
        String actual = c.calculate();
        String expected = "0024";
        assertEquals(expected, actual);
    }

    @Test
    public void whenInputHasValidADOperatorReturnTrue() {
        Calculator c = new Calculator("AD;1;2;3;4;5");
        String actual = c.calculate();
        String expected = "0015";
        assertEquals(expected, actual);
    }

    @Test
    public void whenInputHasMoreThanAllowedVariablesReturn01() {
        Calculator c = new Calculator("AD;111;222;333;444;555;666;777;888;999;100;110");
        String actual = c.calculate();
        String expected = "01";
        assertEquals(expected, actual);
    }

}

