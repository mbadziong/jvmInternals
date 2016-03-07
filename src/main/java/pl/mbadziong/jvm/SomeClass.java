package pl.mbadziong.jvm;

public class SomeClass {

    public PropertyType propertyType;

    public SomeClass(PropertyType type) {
        this.publicPrimitiveProperty = 1337;
        this.publicReferenceProperty = new Object();
        this.propertyType = type;
    }

	public void doNothing(int arg1) {
		
	}

    public int publicPrimitiveProperty;
    public Object publicReferenceProperty;

    public enum PropertyType {
        REFERENCE, PRIMITIVE
    }
}
