package pl.mbadziong.jvm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest implements TestStrategy {

    private final String PRIMITIVE_PROP = "publicPrimitiveProperty";
    private final String REFERENCE_PROP = "publicReferenceProperty";

    @Override
    public void testGet(SomeClass sc) {
        String someClassName = sc.getClass().getName();
        Class<?> someClass;

        String propertyType = sc.propertyType.equals(SomeClass.PropertyType.PRIMITIVE) ?
                PRIMITIVE_PROP :
                REFERENCE_PROP;

        try {
            someClass = Class.forName(someClassName);
            Object someClassInstance = someClass.newInstance();
            Field field = someClass.getDeclaredField(propertyType);
            if(propertyType.equals(PRIMITIVE_PROP)) {
                field.getInt(someClassInstance);
            } else {
                field.get(someClassInstance);
            }

        } catch (Exception e) {
            // who cares
            e.printStackTrace();
        }
    }

    @Override
    public void testSet(SomeClass sc) {
        String someClassName = sc.getClass().getName();
        Class<?> someClass;

        String propertyType = sc.propertyType.equals(SomeClass.PropertyType.PRIMITIVE) ?
                PRIMITIVE_PROP :
                REFERENCE_PROP;

        try {
            someClass = Class.forName(someClassName);
            Object someClassInstance = someClass.newInstance();
            Field field = someClass.getDeclaredField(propertyType);
            if(propertyType.equals(PRIMITIVE_PROP)) {
                field.setInt(someClassInstance, 123);
            } else {
                field.set(someClass, new Object());
            }

        } catch (Exception e) {
            // who cares
            e.printStackTrace();
        }
    }

    @Override
    public void testMethod(SomeClass sc) {
        String someClassName = sc.getClass().getName();
        Class<?> someClass;

        try {
            someClass = Class.forName(someClassName);
            Object someClassInstance = someClass.newInstance();
            String methodName = "doNothing";
            Method setNameMethod = someClass.getMethod(methodName);
            setNameMethod.invoke(someClassInstance, 123);
        } catch (Exception e) {
            // who cares
            e.printStackTrace();
        }
    }
}
