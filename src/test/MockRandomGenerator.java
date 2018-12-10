package test;
import main.java.bot.IRandomGenerator;

public class MockRandomGenerator implements IRandomGenerator{
	public Boolean randomBoolean;
	public String randomString; 
	public Integer randomInt;
	
	public MockRandomGenerator( Boolean randomBoolean) {
		this.randomBoolean = randomBoolean;
	}
	public MockRandomGenerator(String randomString) {
		this.randomString = randomString;
	}
	public MockRandomGenerator(Integer randomInt) {
		this.randomInt = randomInt;
	}

	@Override
	public String generateRandomString(int lenght) {
		return randomString;
	}

	@Override
	public Boolean generateRandomBoolean() {
		return randomBoolean;
	}

	@Override
	public Integer generateRandomInt(int bound) {
		return randomInt;
	}
	@Override
	public String generateRandomInteger(int length, Boolean differentDigits) {
		return randomString;
	}
	
}