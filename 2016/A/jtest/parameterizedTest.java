package suiteTest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.*;

@RunWith(value=Parameterized.class)
public class parameterizedTest
{
	private int A;
	private int B;
	private int C;

	@Parameters
	public static Collection<Integer[]> getTestParameters()
	{
		return Arrays.asList(new Integer[][]
					{
						{2, 3, 1},
						{3, 2, 1},
						{4, 10, 6}
					});
	}

	public parameterizedTest(int A, int B, int C)
	{
		this.A = A;
		this.B = B;
		this.C = C;
	}

	@Test
	public void subTest()
	{
		assertEquals(A, B-C, 0);
	}
}
