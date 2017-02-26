import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.*;

public class third
{
	@Before
	public void firstBefore()
	{
		System.out.println("first B");
	}

	@Before
	public void secondBefire()
	{
		System.out.println("Second B");
	}

	@Test
	public void firstTest()
	{
		System.out.println("first T");
	}

	@Test
	public void secondTest()
	{
		System.out.println("Second T");
	}

	@After
	public void firstAfter()
	{
		System.out.println("First A");
	}

	@After
	public void secondAfter()
	{
		System.out.println("Second A");
	}
	
	
}

