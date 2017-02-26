import static org.junit.Assert.*;
import org.junit.Test;

public class AnnotationTest
{
	@Test(timeout=1000)
	public void Test1()
	{
		Fib(100);
	}

	public int Fib(int n)
	{
		if(n==1 || n==2)
			return 1;
		else
			return Fib(n-1) + Fib(n-2);
	}
}
