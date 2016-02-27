package com.example.jvmint.dcl;

public class Main {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			InterruptedException {
		
		SomeService s0, s1, s2;
		s0 = new ServiceImpl();
		System.out.println("Ss0: " + s0.getClass().getClassLoader());

		s1 = ServiceFactory.newInstance();
		System.out.println("Ss2: " + s1.getClass().getClassLoader());

		while (true) {
			s2 = ServiceFactory.newInstance().sync(s1);
			System.out.println("1. " + s1.message() + " counter: " + s1.incr());
			System.out.println("2. " + s2.message() + " counter: " + s2.incr());

			Thread.sleep(2000);
		}

	}

}
