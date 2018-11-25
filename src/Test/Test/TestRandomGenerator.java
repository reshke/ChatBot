//package Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//import org.junit.Test;
//
//import main.IRandomGenerator;
//import main.RandomGenerator;
//
//public class TestRandomGenerator {
//
//	@Test
//	public void testCreating() {
//	}
//
//	@Test
//	public void testLineSize() {
//		IRandomGenerator generator = new RandomGenerator();
//		String line = generator.generateRandomString(10);
//		assertEquals(line.length(), 10);
//	}
//
//	@Test
//	public void testSymbolsLine() {
//		IRandomGenerator generator = new RandomGenerator();
//		String line = generator.generateRandomString(100);
//		for (int i = 0; i < line.length(); i++) {
//			char symbol = line.charAt(i);
//			if (symbol != '0' && symbol != '1')
//				fail("Line contains wrong symbols!");
//
//		}
//	}
//
//	@Test
//	public void testCreatingBigLine() {
//		IRandomGenerator generator = new RandomGenerator();
//		String line = generator.generateRandomString(100);
//		assertEquals(line.length(), 100);
//	}
//
//	@Test
//	public void testBooleanCreatingNoException() {
//		IRandomGenerator generator = new RandomGenerator();
//		for (int index = 0; index < 1000000; index++) {
//			generator.generateRandomBoolean();
//		}
//
//		assertEquals(true, true);
//	}
//
//	@Test
//	public void testBooleanCreatingDifferent() {
//		IRandomGenerator generator = new RandomGenerator();
//		Integer trueBooleans = 0, falseBooleans = 0;
//		for (int index = 0; index < 1000000; index++) {
//			boolean bool = generator.generateRandomBoolean();
//			if (bool)
//				trueBooleans++;
//			else
//				falseBooleans++;
//		}
//
//		boolean result = falseBooleans == 0 || trueBooleans == 0;
//
//		if (result)
//			fail("Generator creates same bools");
//
//		assertEquals(true, !result);
//	}
//
//	@Test
//	public void testCreatingDifferentIntegersWorks() {
//		IRandomGenerator generator = new RandomGenerator();
//		final int bound = 10000;
//		for (int i = 1; i < bound; i++) {
//			generator.generateRandomInt(i);
//		}
//	}
//
//	@Test
//	public void testBounds() {
//		IRandomGenerator generator = new RandomGenerator();
//		final int bound = 10000;
//		final int repetitions = 1000;
//		for (int i = 0; i < repetitions; i++) {
//			Integer nextNumber = generator.generateRandomInt(bound);
//			if (nextNumber > bound || nextNumber < 0)
//				fail("Generated number in incorrect diaposon!");
//		}
//	}
//}
