package pl.mbadziong.jvm;

public class App {
	public static void main(String[] args) throws Exception {
		final String TEST_BY_REFERENCE = "refer";
		final String TEST_BY_REFLECTION = "refl";

		long attempts;
		String testType;

		if (args.length < 1) {
			throw new Exception("Missing arguments");
		}

		attempts = Long.parseLong(args[0], 10);
		testType = args[1];

		warmup(1000);

		if (testType.equals(TEST_BY_REFERENCE)) {
			test(attempts, new ReferenceTest());
		} else if (testType.equals(TEST_BY_REFLECTION)) {
			test(attempts, new ReflectionTest());
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

	private static void test(long attempts, TestStrategy strategy) {
		SomeClass sc = new SomeClass();
		long startTime = System.currentTimeMillis();

		for (long testNum = 0; testNum < attempts; testNum++) {
			strategy.perform(sc);
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		printResult(elapsedTime);
	}

	private static void printResult(long elapsedMiliseconds) {
		System.out.println(elapsedMiliseconds);
	}
}
