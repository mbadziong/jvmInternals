package pl.mbadziong.jvm;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ArrayList<Object> array = new ArrayList<Object>();
		Boolean condition = true;

		while (condition) {
			array.add(new Object());
			System.out.println("free memory: " + Runtime.getRuntime().freeMemory());
		}
	}
}
