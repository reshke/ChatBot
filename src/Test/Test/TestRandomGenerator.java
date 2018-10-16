package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import main.IRandomGenerator;
import main.RandomGenerator;

public class TestRandomGenerator {
	
	@Test
	public void testCreating() {
		IRandomGenerator generator = new RandomGenerator();
		String line = generator.generateRandomString(10);
	}
	
	@Test
	public void testLineSize() {
		IRandomGenerator generator = new RandomGenerator();
		String line = generator.generateRandomString(10);
		assertEquals(line.length(), 10);
	}
	
	@Test
	public void testSymbolsLine() {
		IRandomGenerator generator = new RandomGenerator();
		String line = generator.generateRandomString(100);
		for (int i = 0; i < line.length(); i++) {
			char symbol = line.charAt(i);
			if (symbol != '0' && symbol != '1')
				fail("Line contains wrong symbols!");
				
		}
	}
	
	@Test
	public void testCreatingBigLine() {
		IRandomGenerator generator = new RandomGenerator();
		String line = generator.generateRandomString(100);
		assertEquals(line.length(), 100);
	}
	
}
