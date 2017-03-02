package org.apache.commons.lang3;

import java.lang.reflect.*;

class SearchMethod
{
	public static void main(String[] args)
	{
		try
		{
			Class c = Class.forName("org.apache.commons.lang3.ArrayUtils");

			Method[] methods = c.getDeclaredMethods();

			System.out.println(c.getPackage());

			for(Method method : methods)
				System.out.println(method.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
