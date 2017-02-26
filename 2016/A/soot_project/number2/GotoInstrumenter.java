
import soot.*;
import soot.jimple.*;
import soot.util.*;
import java.util.*;

public class GotoInstrumenter extends BodyTransformer{
	private static GotoInstrumenter instance = new GotoInstrumenter();
	private boolean addedFieldToMainClassAndLoadedPrintStream=false;
	private SootField gotoCounter,invoke,Ret;
	private SootClass javaIoPrintStream;
	private GotoInstrumenter(){}
	
	public static GotoInstrumenter v(){return instance;}
	
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
			
			invoke = new SootField("invoke",LongType.v(),Modifier.STATIC);
			Scene.v().getMainClass().addField(invoke);
			
			Ret = new SootField("Ret",LongType.v(),Modifier.STATIC);
			Scene.v().getMainClass().addField(Ret);
			
			addedFieldToMainClassAndLoadedPrintStream = true;
		}
		
		boolean isMainMethod = arg0.getMethod().getSubSignature().equals("void main(java.lang.String[])");
		Local tmpLocal=Jimple.v().newLocal("tmp", LongType.v());
		arg0.getLocals().add(tmpLocal);
		Local tmpLocal2 = Jimple.v().newLocal("tmp2", LongType.v());
		arg0.getLocals().add(tmpLocal2);
		Local tmpLocal3 = Jimple.v().newLocal("tmp3", LongType.v());
		arg0.getLocals().add(tmpLocal3);
		Iterator stmIt = arg0.getUnits().snapshotIterator();
		
		while(stmIt.hasNext())
		{
			Stmt s = (Stmt) stmIt.next();
			System.out.println(s);
			if(s instanceof GotoStmt){
				System.out.println("!GOTO!");
				AssignStmt toAdd1 = Jimple.v().newAssignStmt(tmpLocal, Jimple.v().newStaticFieldRef(gotoCounter.makeRef()));
				units.insertBefore(toAdd1, s);
			}
			else if(s instanceof InvokeStmt){
				System.out.println("INVOKESTMT");
				AssignStmt toAdd2 = Jimple.v().newAssignStmt(tmpLocal2, Jimple.v().newStaticFieldRef(invoke.makeRef()));
				units.insertBefore(toAdd2, s);
			}
			else if(isMainMethod && (s instanceof ReturnStmt || s instanceof ReturnVoidStmt))
			{
				AssignStmt toAdd3 = Jimple.v().newAssignStmt(tmpLocal3, Jimple.v().newStaticFieldRef(invoke.makeRef()));
				units.insertBefore(toAdd3, s);
				System.out.println("!REUTURN!");
				
			}
		}
		
		
	}
	
}
