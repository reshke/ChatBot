package Test;
import main.IRandomGenerator;

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
		// TODO Auto-generated method stub
		return randomString;
	}

	@Override
	public Boolean generateRandomBoolean() {
		// TODO Auto-generated method stub
		return randomBoolean;
	}

	@Override
	public Integer generateRandomInt(int bound) {
		// TODO Auto-generated method stub
		return randomInt;
	}
	@Override
	public String generateRandomStringInt(int length, Boolean differentDigits) {
		return randomString;
	}
	
}