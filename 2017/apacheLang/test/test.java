import org.apache.commons.lang3.*;
import java.lang.reflect.*;

class test
{
	public static Class c;

	public static void main(String[] args)
	{
		try
		{
			c = Class.forName("org.apache.commons.lang3.CharSetTest");
			Object obj = c.newInstance();
			Method[] methods = c.getDeclaredMethods();

			for(Method method : methods)
			{
				System.out.println(method.toString());
				method.invoke(obj, new Object[]{});
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Assert");
		}
	}
}
