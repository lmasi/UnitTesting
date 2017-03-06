package org.apache.commons.lang3;

import org.reflections.*;
import org.reflections.util.*;
import org.reflections.scanners.*;

import java.lang.reflect.*;
import java.util.*;

class originClass
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

		HashSet<String> hS = new HashSet<String>();

		System.out.println("===================Origin Classes======================");
		for(Class c : originClasses)
		{
			
			ClassAnalysis CA = new ClassAnalysis(c.getName());
			System.out.println(c.getName());
			System.out.println("{");

			Method[] methods = c.getDeclaredMethods();
			for(Method method : methods)
			{	
				hS.add("\t" + method.getName());
			}

			for(String s : hS)
				System.out.println(s);

			System.out.println("}");
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
