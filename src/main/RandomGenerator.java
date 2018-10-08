package main;
import java.util.Random;


public class RandomGenerator {
	private final Random random = new Random();
	
	public String GenerateRandomString(int lenght){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < lenght; i++){
			builder.append(random.nextBoolean() ? '1' : '0');
		}
		
		return builder.toString();
	}
	
	public Boolean GenerateRandomBoolean(){
		return random.nextBoolean();
	}
	
	public Integer GenerateRandomInt(int bound){
		return random.nextInt(bound);
	}
}
