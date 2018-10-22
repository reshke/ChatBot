package main;
import java.util.Random;

import org.glassfish.grizzly.utils.ArrayUtils;


public class RandomGenerator implements IRandomGenerator {
	private final Random random = new Random();
	
	public String generateRandomString(int lenght){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < lenght; i++){
			builder.append(random.nextBoolean() ? '1' : '0');
		}
		
		return builder.toString();
	}
	
	public Boolean generateRandomBoolean(){
		return random.nextBoolean();
	}
	
	public Integer generateRandomInt(int bound){
		return random.nextInt(bound);
	}
	
	public String GenerateRandomSequence(int lenght){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < lenght; i++){
			builder.append(random.nextInt(10));
		}
		
		return builder.toString();
	}

	@Override
	public String generateRandomStringInt(int length, Boolean differentDigits) {
		if (!differentDigits)
			return null;
		else {
			if (length > 10 || length < 0)
				throw new IllegalArgumentException("Length should be in diaposon from 1 to 10");
			String result = "";
			String digits = "0123456789";
			StringBuilder build = new StringBuilder(digits);
			for (Integer index = 0; index < length; index++) {
				int indexChar = generateRandomInt(build.length());
				result += build.charAt(indexChar);
				build.deleteCharAt(indexChar);
			}
			return result;
		}
	}
}
