package pl.mbadziong.jvm;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ArrayList arr = new ArrayList();
    	Boolean cond = true;
    	
    	int i = 0;
        while(cond) {
        	arr.add(new Object());
        	if(i%100000==0) {
        		System.out.println("free memory: " + Runtime.getRuntime().freeMemory());
        	}
        	i++;
        }
    }
}
