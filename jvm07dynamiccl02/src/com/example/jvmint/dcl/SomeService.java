package com.example.jvmint.dcl;

public interface SomeService {
	
	String message();
	int incr();
	int counter();
	
	SomeService sync(SomeService src);
}
