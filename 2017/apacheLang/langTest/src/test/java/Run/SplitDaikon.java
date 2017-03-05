import java.io.*;
import java.util.ArrayList;

class SplitDaikon
{
	private ArrayList<DaikonBox> daikonBox = new ArrayList<DaikonBox>();

	private SplitDaikon()
	{
	}

	SplitDaikon(File daikonFile) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(daikonFile));
		String tmp;
		String unitBox = "";
		boolean flag = false;

		while((tmp = br.readLine()) != null)
		{
			if(!flag && unitBox.equals("") && tmp.indexOf("===") >=0)
			{
				flag = true;
			}

			else if(flag && tmp.indexOf("===") >= 0)
			{
				flag = false;
				daikonBox.add(new DaikonBox(unitBox));
				unitBox = "";
			}

			else if(flag)
			{
				unitBox += tmp + "\n";
			}
		}
	}

	public ArrayList<DaikonBox> getBox()
	{
		return daikonBox;
	}
}
