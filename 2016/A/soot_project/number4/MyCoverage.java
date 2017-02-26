
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import soot.Local;

public class MyCoverage {
	private static int count=0;
    private static int number=0;
    
    static  File file = new File("test.gcda");

    
    public static synchronized void increaseCount(int howmany){
            count+=howmany;
    }

    public static synchronized void printBlock(){
            System.err.println("Block: " + number);
            System.err.println("count: " + count);
    }

    public static synchronized void makeFile(){
	    	try{
	            FileWriter fw = new FileWriter(file,true);
	            fw.close();
	    }catch(Exception ex){
	            System.out.println(ex.getMessage());
	    }
    
    }
    public static synchronized void printString(java.lang.String a){
    	
    	StringTokenizer st = new StringTokenizer(a);
    	String s = "";
    	while(st.hasMoreTokens()){
    		String temp = st.nextToken();
    		if(temp.equals("goto")) break;
    		else if(!temp.equals("if")){
    			s = s+temp;
    		}
    		
    	}
    	System.out.println(s);
    	
    }
    public static synchronized void printInt(int a){
    	System.out.println(a);
    }
    public static synchronized void printLocal(soot.Local a){
    	System.out.println("hi!!");
    }
    
    public static synchronized void update(int i){
        try{
                String dummy="";
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                
                String s;
                String block = "block"+i;
                int chk=0;
                int value = 1;
                while((s=br.readLine())!=null){
                        if(block.equals(s)){
                                chk=1;
                                dummy += s+"\n";
                                value = Integer.parseInt(br.readLine())+1;
                                dummy += String.valueOf(value)+"\n";
                        }
                        else{
                                dummy += s+"\n";
                        }
                }
                if(chk==0){
                        dummy+= block+"\n";
                        dummy+="1\n";
                }


                FileWriter fw = new FileWriter(file);
                fw.write(dummy);

                fw.close();
                br.close();
        }catch(IOException e){
                System.err.println(e);
                System.exit(1);
        }
}

}

