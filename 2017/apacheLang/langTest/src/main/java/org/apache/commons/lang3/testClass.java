package org.apache.commons.lang3;

import org.reflections.*;
import org.reflections.util.*;
import org.reflections.scanners.*;

import java.lang.reflect.*;
import java.util.*;

class testClass
{
	public static void main(String[] args)
	{
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder()
				.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
				.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
				.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("org.apache.commons.lang3"))));

		Set<Class<? extends Object>> tmpClasses = reflections.getSubTypesOf(Object.class);

		ArrayList<Class<? extends Object>> allClasses = new ArrayList();
		ArrayList<Class<? extends Object>> TestClasses = new ArrayList();
		ArrayList<Class<? extends Object>> originClasses = new ArrayList();

		for(Class c : tmpClasses)
			allClasses.add(c);

		Collections.sort(allClasses, new ClassNameSort());

		for(Class c : allClasses)
		{
			if(c.getName().toString().indexOf("$") > 0)
				continue;

			else if(c.getName().toString().indexOf("Test") > 0)
				TestClasses.add(c);

			else
				originClasses.add(c);
		}

		System.out.println("===================Test Classes======================");
		for(Class c : TestClasses)
		{
			ClassAnalysis CA = new ClassAnalysis(c.getName());
	
			Method[] methods = c.getDeclaredMethods();

			System.out.println(c.getName());
			System.out.println("{");
			for(Method method : methods)
			{	
				System.out.println("\t" + method.getName());
			}
			System.out.println("}");
			System.out.println("\n");
		}
	}

	static class ClassNameSort implements Comparator<Class<? extends Object>> {

		@Override
			public int compare(Class c1, Class c2) {
				// TODO Auto-generated method stub
				return c1.toString().compareTo(c2.toString());
			}

	}
}
