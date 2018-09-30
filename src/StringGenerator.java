import java.util.Random;

/**
 * 
 */

/**
 * @author reshke && Chishov
 *
 */

public class StringGenerator {
	final Random random = new Random();
	
	public String GenerateRandomString(int lenght){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < lenght; i++){
			builder.append(random.nextBoolean());
		}
		
		return builder.toString();
	}
}
