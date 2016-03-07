package pl.mbadziong.jvm;

import java.lang.reflect.Method;

public class ReflectionTest implements TestStrategy {

	@Override
	public void perform(SomeClass sc) {
		String someClassName = sc.getClass().getName();
		Class<?> someClass;

		try {
			someClass = Class.forName(someClassName);
			Object someClassInstance = someClass.newInstance();
			String methodName = "doNothing";
			Method setNameMethod = someClass.getMethod(methodName);
			setNameMethod.invoke(someClassInstance);
		} catch (Exception e) {
			// who cares
			e.printStackTrace();
		}
	}
}
