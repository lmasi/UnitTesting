package org.apache.commons.lang3;

import java.lang.reflect.*;
import java.util.ArrayList;

class ClassAnalysis
{
	private Class forClass;

	public ClassAnalysis(String className)
	{
		try{
		this.forClass = Class.forName(className);}catch(Exception e){e.printStackTrace();}
	}

	public String getAnalysis()
	{
		return  forClass.getName() + "  (" + getPublicMethodsCount() + ", " + getNotPublicMethodsCount() + ")";
	}

	public Method[] getDeclaredMethods()
	{
		return forClass.getDeclaredMethods();
	}

	public int getMethodsCount()
	{
		int i = 0;

		Method[] methods = forClass.getDeclaredMethods();

		for(Method method : methods)
			i++;

		return i;
	}

	public int getPublicMethodsCount()
	{
		int i = 0;

		Method[] methods = forClass.getDeclaredMethods();

		for(Method method : methods)
		{
			if(Modifier.isPublic(method.getModifiers()))
				i++;
		}

		return i;
	}
	
	public int getNotPublicMethodsCount()
	{
		int i = 0;

		Method[] methods = forClass.getDeclaredMethods();

		for(Method method : methods)
		{
			if(!(Modifier.isPublic(method.getModifiers())))
				i++;
		}

		return i;
	}

}
