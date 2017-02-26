
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
        private Value pp;
        static SootClass coverageClass;
        static SootMethod printBlock, increaseCount,update,printString,printInt,printLocal;
        static{
                coverageClass = Scene.v().loadClassAndSupport("MyCoverage");
                printString = coverageClass.getMethod("void printString(java.lang.String)");
                printInt = coverageClass.getMethod("void printInt(int)");
                printLocal = coverageClass.getMethod("void printLocal(soot.Local)");
                
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
                
                
                
                Iterator stmIt = arg0.getUnits().snapshotIterator();
                
                
               
                
                while(stmIt.hasNext())
                {
                        Stmt s = (Stmt) stmIt.next();
                        
                        
                        System.out.println(s);                    
                        
                        if(s instanceof IfStmt){
                        	System.out.println("================================");
                        	System.out.println(((IfStmt) s).getCondition().getType().getClass().toString());
                        	
                        	/*
                        	Local tmp = Jimple.v().newLocal("dd",BooleanType.v());
                        	arg0.getLocals().add(tmp);
                        	AssignStmt toAdd = Jimple.v().newAssignStmt(tmp, (Value) ((IfStmt) s).getCondition().clone());
                        	units.insertBefore(toAdd, s);
                        	*/
                        	System.out.println("================================");
                        	
                        }
                        
                        /*
                        List<ValueBox> defBoxes = s.getUseBoxes();
                        for(int i = 0; i < defBoxes.size();i++){
                        	System.out.println("********************************************");
                        	System.out.println(defBoxes.get(i).getValue().);
                        	InvokeExpr printLocalExpr = Jimple.v().newStaticInvokeExpr(printInt.makeRef(),IntConstant.v());
                            Stmt printLocalStmt = Jimple.v().newInvokeStmt(printLocalExpr);
                            units.insertBefore(printLocalStmt,s);
                        	System.out.println("********************************************");
                        }
                        */
                        
                        /*
                        if(s instanceof IfStmt){
                        	System.out.println("!!!!IFIF!!!!!");
                        	 Local tmpLocal = Jimple.v().newLocal("tmp1",LongType.v());
                        	 arg0.getLocals().add(tmpLocal);
                        	 AssignStmt toAdd1 = Jimple.v().newAssignStmt(tmpLong, Jimple.v().newStaticFieldRef(gotoCounter.makeRef()));
                        	 
                        	 Local tmp=Jimple.v().newLocal("tmp", IntType.v());
                             arg0.getLocals().add(tmp);
                             
                             Local tmpInt=Jimple.v().newLocal(first.getName(), IntType.v());
                             arg0.getLocals().add(tmpInt);
                             
                             AssignStmt toAdd = Jimple.v().newAssignStmt(tmpInt, tmp);
                        	units.insertBefore(toAdd, s);
                        	System.out.println("!!");
                        }
                        
                        */
                        /*
                        if(s instanceof IfStmt){
                                System.out.println("!!!!IFIF!!!!!");
                                String tmp = s.toString();
                                System.out.println("****************************************************");
                                
                                InvokeExpr printLocalExpr = Jimple.v().newStaticInvokeExpr(printLocal.makeRef(),first);
                                Stmt printLocalStmt = Jimple.v().newInvokeStmt(printLocalExpr);
                                units.insertBefore(printLocalStmt,s);
                                
                        }
*/

                }


        }

}

/*
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
	static SootMethod printBlock, increaseCount,update,makeFile,printInt;
	List <SootClass> df = new Vector<SootClass>();
	static{
		coverageClass = Scene.v().loadClassAndSupport("MyCoverage");
		printBlock = coverageClass.getMethod("void printBlock()");
		increaseCount = coverageClass.getMethod("void increaseCount(int)");
		update = coverageClass.getMethod("void update(int)");
		makeFile = coverageClass.getMethod("void makeFile()");
		printInt = coverageClass.getMethod("void printInt(int)");
	}
	
	@Override
	protected void internalTransform(Body arg0, String arg1, Map arg2) {
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
		
		PatchingChain<Unit> u = arg0.getUnits();
		for(Iterator uIt = u.iterator(); uIt.hasNext();){
			Unit unit=(Unit)uIt.next();
			int lineNum=unit.getJavaSourceStartLineNumber(); 
			System.out.println("\t"+lineNum+": " + unit);
		}
		
		InvokeExpr updateExpr = Jimple.v().newStaticInvokeExpr(update.makeRef(),IntConstant.v(i++));
		Stmt updateStmt = Jimple.v().newInvokeStmt(updateExpr);
		
		
		int i = 0;
		while(stmIt.hasNext())
		{
			
			Stmt s = (Stmt) stmIt.next();
			System.out.println(s);
			String tmp = s.toString();
			
			if(s instanceof IfStmt)
			{
				System.out.println("!!!IF!!!");
				System.out.println();
				
			}
			
			InvokeExpr printExpr = Jimple.v().newStaticInvokeExpr(printInt.makeRef(),IntConstant.v(i));
		
			Stmt printStmt = Jimple.v().newInvokeStmt(printExpr);
	
			units.insertBefore(printStmt, s);
			

		}
		

	}
	
}
*/
