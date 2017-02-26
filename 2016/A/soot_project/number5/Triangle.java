

import java.util.*;
public class Triangle {
        public static void main(String args[]){
                Scanner st = new Scanner(System.in);
                int x, y, z;
                System.out.print("Input the x: ");
                x = st.nextInt();
                System.out.print("Input the y: ");
                y = st.nextInt();
                System.out.print("Input the z: ");
                z = st.nextInt();
                System.out.println("x: " + x + " y: " + y + " z: " + z);

                
               
                if(x+y>z && y+z>x && x+z>y){

                        if(x == y && y==z ) System.out.println("0");
                        else if(x==y || x==z || y==z) System.out.println("1");
                        else System.out.println("2");
                }
               
                else System.out.println("3");
               
                /*
                if(!(x+y>z && y+z>x && x+z>y))
                    System.out.println("3");
                else if(x==y && y==z)
                    System.out.println("0");
                else if(x==y||y==z||z==x)
                    System.out.println("1");
                else
                    System.out.println("2");
                
                System.out.println("asdf");
                
                for(int i=0; i < 10; i++){
                	System.out.println("dfdfasd");
                }
                
                
                
                if(!(x+y>z && y+z>x && x+z>y))
                    System.out.println("3");
                else if(x==y && y==z)
                    System.out.println("0");
                else if(x==y||y==z||z==x)
                    System.out.println("1");
                else
                    System.out.println("2");
                */
          }

        
        
}


