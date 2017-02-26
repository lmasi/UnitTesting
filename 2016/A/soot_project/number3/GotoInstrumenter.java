
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
	static SootMethod printBlock, increaseCount,update;
	List <SootClass> df = new Vector<SootClass>();
	static{
		coverageClass = Scene.v().loadClassAndSupport("MyCoverage");
		printBlock = coverageClass.getMethod("void printBlock()");
		increaseCount = coverageClass.getMethod("void increaseCount(int)");
		update = coverageClass.getMethod("void update()");
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
		
		while(stmIt.hasNext())
		{
			Stmt s = (Stmt) stmIt.next();
			System.out.println(s);
			System.out.println(s.getBoxesPointingToThis().size());
			if(s instanceof GotoStmt){
				System.out.println("!GOTO!");
				
				InvokeExpr updateExpr = Jimple.v().newStaticInvokeExpr(update.makeRef());
				Stmt updateStmt = Jimple.v().newInvokeStmt(updateExpr);
				units.insertBefore(updateStmt, s);
				
				InvokeExpr incExpr = Jimple.v().newStaticInvokeExpr(increaseCount.makeRef(), IntConstant.v(1));
				Stmt incStmt = Jimple.v().newInvokeStmt(incExpr);
				units.insertBefore(incStmt, s);
				
				InvokeExpr reportExpr = Jimple.v().newStaticInvokeExpr(printBlock.makeRef());
				Stmt reportStmt = Jimple.v().newInvokeStmt(reportExpr);
				units.insertBefore(reportStmt, s);
				
			}
			
			
		}
		

	}
	
}
