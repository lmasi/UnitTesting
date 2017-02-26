import soot.*;
import soot.jimple.*;
import soot.util.*;
import java.io.*;
import java.util.*;

/** 
   Example to instrument a classfile to produce goto counts. 
 */
public class Main
{
    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            System.out.println("Syntax: java ashes.examples.instrumentclass.Main [soot options]");
            System.exit(0);
        }
        
        Pack jtp = PackManager.v().getPack("jtp");
        jtp.add(new Transform("jtp.instrumenter", GotoInstrumenter.v()));
        soot.Main.main(args);
    }
}

