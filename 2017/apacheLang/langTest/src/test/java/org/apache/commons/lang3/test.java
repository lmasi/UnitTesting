package org.apache.commons.lang3;

import java.lang.reflect.*;

class test
{
	public static Class c;

	public static String[] str = new String[]
	{
		"StringEscapeUtilsTest",
			"StringUtilsContainsTest",
			"StringUtilsEmptyBlankTest",
			"StringUtilsEqualsIndexOfTest",
			"StringUtilsIsTest",
			"StringUtilsStartsEndsWithTest",
			"StringUtilsSubstringTest",
			"StringUtilsTest",
			"StringUtilsTrimStripTest",
			"SystemUtilsTest"
	};

	public static void main(String[] args)
	{
		try
		{
			for(String className : str)
			{

				c = Class.forName("org.apache.commons.lang3." + className);

				Object obj = c.newInstance();

				Method[] methods = c.getDeclaredMethods();

				for(Method method : methods)
				{	
					if(method.getName().toString().indexOf("test") >= 0 && Modifier.isPublic(method.getModifiers()))
					{	
						System.out.println(c.getName() + " : " + method.getName());
						try{method.invoke(obj, new Object[]{});}catch(Exception e){e.printStackTrace();}
					}
				}
			}
		}catch(Exception e){e.printStackTrace();}
		System.out.println("Assert");
	}

}

