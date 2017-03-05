import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.*;

class RunTest
{
	public static void main(String[] args)
	{
		String javaName = args[0];
		String daikonName = args[1];

		System.out.println(javaName + "\n" + daikonName);

		String total = "";

		try
		{
			BufferedWriter bw_tmp = new BufferedWriter(new FileWriter(new File("tmp")));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("result")));

			Process p = Runtime.getRuntime().exec("java daikon.Chicory --daikon " + javaName);

			String tmp;
			BufferedReader tmpOut = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while((tmp = tmpOut.readLine()) != null)
				System.out.println(tmp);

			while(p.isAlive());
			System.out.println("Chikory end");

			Process q = Runtime.getRuntime().exec("gzip -d " + daikonName + ".inv.gz");
			while(q.isAlive());
			Process oProcess = Runtime.getRuntime().exec("java daikon.PrintInvariants " + daikonName + ".inv");


			String s;
			BufferedReader stdOut   = new BufferedReader(new InputStreamReader(oProcess.getInputStream()));

			while ((s =   stdOut.readLine()) != null)
			{
				System.out.println(s);
				bw_tmp.write(s + "\n");
			}

			bw_tmp.close();

			// result
			SplitDaikon daikon = new SplitDaikon(new File("tmp"));

			ArrayList<DaikonBox> daikonList = daikon.getBox();
			Set<String> set = new HashSet<String>();

			for(DaikonBox daikonbox : daikonList)
			{
				set.add(new String(daikonbox.getOriginClass("org.apache.commons.lang3")));
			}

			BufferedWriter inputClasses = new BufferedWriter(new FileWriter(new File("input")));

			for(String ss : set)
				inputClasses.write("../../main/java/org/apache/commons/lang3/"+ ss +".java" + "\n");
			inputClasses.close();

			Finals finals = new Finals();
			ArrayList<String> finalValues = finals.getFinals(new File("input"));

			BufferedWriter bw_daikonMethods = new BufferedWriter(new FileWriter(new File("./results/daikonMethods")));
			Set<String> daikonSet = new HashSet<String>();

			for(DaikonBox daikonbox : daikonList)
			{
				if(daikonbox.getTitle() != null && !(daikonbox.getTitle().indexOf("Test") >= 0) && !(daikonbox.getTitle().indexOf("junit") >= 0) && !(daikonbox.getTitle().indexOf("test") >=0))
				{
					System.out.println(daikonbox.toString());

					BufferedWriter bw_daikon = new BufferedWriter(new FileWriter(new File("./results/" + daikonbox.getOriginClass("org.apache.commons.lang3")), true));

					daikonSet.add(daikonbox.getTitle() + "\n");

					ArrayList<String> invariants = daikonbox.getInvariants();

					String stmp = "";
					for(int i=0; i<20; i++)
						stmp += "=";
					stmp += "\n" + daikonbox.getTitle();
					stmp += ":::" + daikonbox.getType();
					bw.write(stmp + "\n");					
					bw_daikon.write(stmp + "\n");

					for(String invariant : invariants)
					{
						boolean flag = true;
						
						for(String f : finalValues)
							flag &= !(invariant.indexOf(f) >= 0);

						if(flag)
						{
							bw.write(invariant + "\n");
							bw_daikon.write(invariant +"\n");
						}
					}
				
					bw_daikon.close();
				}
			}
			for(String stmp : daikonSet)
				bw_daikonMethods.write(stmp);

			bw_daikonMethods.close();
			bw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("well");
		}
	}


}
