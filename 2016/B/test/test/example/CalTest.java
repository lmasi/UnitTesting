package example;

import org.junit.Test;
import static org.junit.Assert.*;

class CalTest
{
	@Test
	public void testCalc()
	{
		Calculator calc = new Calculator();
	
		assertEquals(10, calc.calc_linear(10));
	}
}
