package com.example.jvmint.dcl;

public class ServiceImpl implements SomeService {
	
	private int counter;

	@Override
	public String message() {
		return "Jestem plikiem .class lokalnym.";
	}

	@Override
	public int incr() {
		return counter++;
	}

	@Override
	public int counter() {
		return counter;
	}
	
	@Override
	public SomeService sync(SomeService src) {
		counter = src.counter();
		return this;
	}
	

}
