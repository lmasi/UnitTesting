
import soot.*;
import soot.jimple.*;
import soot.options.Options;
import soot.tagkit.Tag;
import soot.util.*;
import java.util.*;
import soot.tagkit.*;
public class GotoInstrumenter extends BodyTransformer{
	private static GotoInstrumenter instance = new GotoInstrumenter();
	private boolean addedFieldToMainClassAndLoadedPrintStream=false;
	private SootField gotoCounter;
	private SootClass javaIoPrintStream;
	private GotoInstrumenter(){}
	public static GotoInstrumenter v(){return instance;}
	static SootClass coverageClass;
	static SootMethod printBlock, increaseCount,update,makeFile;
	List <SootClass> df = new Vector<SootClass>();
	@Override
	protected void internalTransform(Body arg0, String arg1, Map arg2) {
		Options.v().set_keep_line_number(true);
		// TODO Auto-generated method stub
		if(!Scene.v().getMainClass().declaresMethod("void main(java.lang.String[])"))
			throw new RuntimeException("main fail");
		Chain units = arg0.getUnits();
		SootMethod method = arg0.getMethod();
		System.out.println("instrumenting method : " + method.getSignature());
		if(addedFieldToMainClassAndLoadedPrintStream)
			gotoCounter = Scene.v().getMainClass().getFieldByName("gotoC");
		else{
			gotoCounter = new SootField("gotoC",LongType.v(),Modifier.STATIC);
			Scene.v().getMainClass().addField(gotoCounter);
			//Scene.v().loadClassAndSupport("java.io.PrintStream");
			javaIoPrintStream = Scene.v().getSootClass("java.io.PrintStream");
			
			addedFieldToMainClassAndLoadedPrintStream = true;
		}
		
		boolean isMainMethod = arg0.getMethod().getSubSignature().equals("void main(java.lang.String[]");
		Local tmpLocal=Jimple.v().newLocal("tmpLong", LongType.v());
		
		arg0.getLocals().add(tmpLocal);
	
		Iterator stmIt = arg0.getUnits().snapshotIterator();
		
		
		
		while(stmIt.hasNext())
		{
			
			Stmt s = (Stmt) stmIt.next();
			if(s.getBoxesPointingToThis().size()!=0){
				System.out.println("\t\t\t\tLABEL!!!");
			}
			System.out.println(s);
			String tmp = s.toString();
			
			List<UnitBox> dd =s.getUnitBoxes(); 
			
			for(int x = 0 ; x < dd.size();x++){
				String st = dd.get(x).toString();
				System.out.println("**THIS ADDRESS: "+st);
				System.out.println("**NEXT STATEMENT: "+ dd.get(x).getUnit().toString());
				System.out.println("** ADDRESS: "+st.substring(28));
				
			}
			
			
			List<UnitBox> qq = s.getBoxesPointingToThis();
			
			for(int x = 0; x < qq.size();x++){
				String st = qq.get(x).toString();
				System.out.println(qq.get(x).getUnit().toString());
				System.out.println(st);
				System.out.println(st.substring(28));
			}
			
			
			if(s.getBoxesPointingToThis().size()!=0){
				System.out.println("\t\t\tLABEL!");	
			}
			
			
			
	
		}

	}
	
}
