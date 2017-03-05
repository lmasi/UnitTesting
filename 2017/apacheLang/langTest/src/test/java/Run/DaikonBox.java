import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class DaikonBox
{
	private String content;
	private String title;
	private ArrayList<String> invariants = new ArrayList<String>();
	private String type;

	DaikonBox(String content)
	{
		this.content = content;

		String[] strs = content.split("\n");
	
		for(String s : strs)
		{
			if(s.indexOf("ENTER") >=0)
			{
				this.type = "ENTER";
				title = new String(s.substring(0, s.indexOf(":::")));
			}
			else if(s.indexOf("EXIT") >= 0)
			{
				this.type = "EXIT";
				title = new String(s.substring(0, s.indexOf(":::")));
			}
			else
				invariants.add(new String(s));
		}
	}

	public String getType()
	{
		return this.type;
	}

	public String getTitle()
	{
		return this.title;
	}

	public ArrayList<String> getInvariants()
	{
		return this.invariants;
	}

	public String getContent()
	{
		return this.content;
	}
	
	public String getOriginClass(String packageName)
	{
		if(title == null) return "";
		String originClass = title.replace(packageName+".", "");
		originClass = originClass.substring(0, originClass.indexOf("."));
		return originClass;
	}

	public String toInfoString()
	{
		String tmp = "";
		tmp += "Title : " + this.title + "\n";
		tmp += "Type : " + this.type + "\n";
		for(String s : invariants)
			tmp += s + "\n";
		return tmp;
	}

	@Override
	public String toString()
	{
		String tmp = "";
		for(int i=0; i<20; i++)
			tmp += "=";
		tmp += "\n" + this.content;

		return tmp;
	}

	public void removeInvariants(String invariant)
	{
		this.invariants.remove(invariant);
	}
}
