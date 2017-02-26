import java.io.*;
public class MyCoverage{
	private static int count=0;
	private static int number=0;
	private static int i=0;	
	static	File file = new File("test.gcda");
	
	MyCoverage(){
	try{
		FileWriter fw = new FileWriter(file,true);
		fw.close();
	}catch(Exception ex){
		System.out.println(ex.getMessage());
	}
	}
	public static synchronized void increaseCount(int howmany){
		count+=howmany;
	}

	public static synchronized void printBlock(){
		System.err.println("Block: " + number);
		System.err.println("count: " + count);
	}
public static synchronized void update(){
                try{
                        String dummy="";
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

                        String s;
                        String block = "block"+(i++);
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
