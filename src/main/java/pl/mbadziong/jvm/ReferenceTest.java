package pl.mbadziong.jvm;

public class ReferenceTest implements TestStrategy {

    private final String PRIMITIVE_PROP = "publicPrimitiveProperty";
    private final String REFERENCE_PROP = "publicReferenceProperty";

    @Override
    public void testGet(SomeClass sc) {
        if(sc.propertyType.equals(PRIMITIVE_PROP)) {
            int x = sc.publicPrimitiveProperty;
        } else {
            Object o = sc.publicReferenceProperty;
        }
    }

    @Override
    public void testSet(SomeClass sc) {
        if(sc.propertyType.equals(PRIMITIVE_PROP)) {
            sc.publicPrimitiveProperty++;
        } else {
            sc.publicReferenceProperty = new Object();
        }
    }

    @Override
    public void testMethod(SomeClass sc) {
        sc.doNothing(3);
    }
}
