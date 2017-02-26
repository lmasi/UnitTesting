package suiteTest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class assertArrayEqualTest
{
        @Test
        public void test()
        {
		String str1 = new String("Hello");
		String str2 = new String("Hello");

		assertSame(str1, str2);
        }
}

