import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class  second
{
	int a = 0;

	@Before
	public void seta()
	{
		a = 10;
	}

	@After
	public void printa()
	{
		System.out.print("first After : ");
		System.out.println(a);
	}

	@After
	public void printb()
	{
		System.out.println("second After : "  + a);
	}

	@Test
	public void addtest()
	{
		assertEquals(10,a,0);
		a = 12;
	}

	@Test
	public void addtest2()
	{
		assertEquals("Error in 2",10,a,0);
		a = 10;
	}
}
