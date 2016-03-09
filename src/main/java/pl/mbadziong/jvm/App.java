package pl.mbadziong.jvm;

public class App {

	public static void main(String[] args) throws Exception {
		final String TEST_BY_REFERENCE = "refer";
		final String TEST_BY_REFLECTION = "refl";
        final String PROPERTY_OBJECT = "obj";
        final String PROPERTY_INT = "int";

		long attempts;
		String testType;
        SomeClass.PropertyType propertyType;

		if (args.length < 1) {
			throw new Exception("Missing arguments");
		}

		attempts = Long.parseLong(args[0], 10);
		testType = args[1];
        propertyType = args[2].equals(PROPERTY_OBJECT) ?
                SomeClass.PropertyType.REFERENCE :
                SomeClass.PropertyType.PRIMITIVE;

		warmup(1000);

		if (testType.equals(TEST_BY_REFERENCE)) {
			test(attempts, new ReferenceTest(), propertyType);
		} else if (testType.equals(TEST_BY_REFLECTION)) {
			test(attempts, new ReflectionTest(), propertyType);
		} else {
			throw new Exception("Wrong test type");
		}
	}

	private static int warmup(long attempts) {
		int sum = 0;
		for (long testNum = 0; testNum < attempts; testNum++) {
			sum += testNum;
		}
		return sum;
	}

	private static void test(long attempts, TestStrategy strategy, SomeClass.PropertyType chosenPropertyType) {
		SomeClass sc = new SomeClass(chosenPropertyType);
		long startTime = System.currentTimeMillis();

		for (long testNum = 0; testNum < attempts; testNum++) {
			//strategy.testGet(sc);
            //strategy.testSet(sc);
            strategy.testMethod(sc);
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		printResult(elapsedTime);
	}

	private static void printResult(long elapsedMiliseconds) {
		System.out.println(elapsedMiliseconds);
	}
}
