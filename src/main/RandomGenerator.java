package main;
import java.util.Random;


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
}
