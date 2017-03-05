import java.io.*;
import java.util.*;

class Finals
{
	Finals()
	{
	}

	public ArrayList<String> getFinals(String fileName)
	{

		ArrayList<String> finals = new ArrayList<String>();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));

			String str;

			while((str = br.readLine()) != null)
			{
				if(str.indexOf("public static final") >= 0 || str.indexOf("private static final") >=0 || str.indexOf("protected static final")>=0)
				{
					String[] arr = str.split(" ");
					finals.add(arr[8]);
				}
			}

			return finals;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return finals;
	}

	public ArrayList<String> getFinals(File file) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String tmp;
		ArrayList<String> arr = new ArrayList<String>();

		while((tmp = br.readLine()) != null)
		{
			try
			{
				ArrayList<String> arr_tmp = getFinals(tmp);
				for(String str : arr_tmp)
					arr.add(str);
			}catch(Exception e)
			{
			}
		}

		return arr;
	}
}
