package suiteTest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses(value = {assertArrayEqualTest.class, parameterizedTest.class} )
class suiteTest
{
	public static void  suite()
	{
	}
}
