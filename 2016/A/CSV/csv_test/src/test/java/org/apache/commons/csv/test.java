package org.apache.commons.csv;

import java.lang.reflect.*;

class test
{
	public static void main(String[] args)
	{
		try{

			Class c = LexerTest.class;
			Object obj = c.newInstance();

			Method[] m = c.getDeclaredMethods();
			

			for(int i=0; i<m.length; i++)
			{try{
				if(m[i].getModifiers() == 1)
				{
					System.out.println(m[i].getName().toString());
					m[i].invoke(obj);
				}
			    }
			catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("reason : " + m[i].getName().toString());
				}
			}
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
