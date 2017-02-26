
import soot.*;
import soot.jimple.*;
import soot.util.*;
import java.util.*;

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
	static{
		coverageClass = Scene.v().loadClassAndSupport("MyCoverage");
		printBlock = coverageClass.getMethod("void printBlock()");
		increaseCount = coverageClass.getMethod("void increaseCount(int)");
		update = coverageClass.getMethod("void update(int)");
		makeFile = coverageClass.getMethod("void makeFile()");
	}
	@Override
	protected void internalTransform(Body arg0, String arg1, Map arg2) {
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
		
		int i = 0;
		while(stmIt.hasNext())
		{
			
			Stmt s = (Stmt) stmIt.next();
			if(s.getBoxesPointingToThis().size()!=0){
				System.out.println("\t\t\t\tLABEL!!!");

				InvokeExpr updateExpr = Jimple.v().newStaticInvokeExpr(update.makeRef(),IntConstant.v(i++));
				Stmt updateStmt = Jimple.v().newInvokeStmt(updateExpr);
				units.insertAfter(updateStmt, s);
			}
			System.out.println(s);
			String tmp = s.toString();
			
			if(tmp.equals("r0 := @parameter0: java.lang.String[]")){
				InvokeExpr makeFileExpr = Jimple.v().newStaticInvokeExpr(makeFile.makeRef());
				Stmt makeFileStmt = Jimple.v().newInvokeStmt(makeFileExpr);
				units.insertAfter(makeFileStmt, s);
			}
			
			
			
	
		}
		

	}
	
}
