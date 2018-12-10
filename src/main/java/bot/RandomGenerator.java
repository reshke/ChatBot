package main.java.bot;
import java.io.Serializable;
import java.util.Random;

public class RandomGenerator implements IRandomGenerator, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2658224816119274235L;
	private final Random random = new Random();
	
	public String generateRandomString(int lengthString){
		StringBuilder genereatedString = new StringBuilder();
		for (int i = 0; i < lengthString; i++){
			genereatedString.append(random.nextBoolean() ? '1' : '0');
		}
		
		return genereatedString.toString();
	}
	
	public Boolean generateRandomBoolean(){
		return random.nextBoolean();
	}
	
	public Integer generateRandomInt(int bound){
		return random.nextInt(bound);
	}
	
	public String generateRandomSequence(int lenghtSequence){
		StringBuilder sequence = new StringBuilder();
		for (int i = 0; i < lenghtSequence; i++){
			sequence.append(random.nextInt(10));
		}
		return sequence.toString();
	}

	@Override
	public String generateRandomInteger(int integerDigits, Boolean differentDigits) {
		raiseIfArgumentForCreatingRandomIntegerAreIncorrect(integerDigits, differentDigits);
		String createdInteger = "";
		StringBuilder freeDigits = new StringBuilder("0123456789");
		for (Integer index = 0; index < integerDigits; index++)
			createdInteger += getNextFreeDigit(freeDigits);
		return createdInteger;
	}
	
	private void raiseIfArgumentForCreatingRandomIntegerAreIncorrect(Integer integerDigits, Boolean differentDigits) {
		if (!differentDigits)
			throw new UnsupportedOperationException("Generator can't create a number with not different digits!");
		if (integerDigits > 10 || integerDigits < 0)
			throw new IllegalArgumentException("Length should be in diaposon from 1 to 10");
		
	}
	
	private char getNextFreeDigit(StringBuilder freeDigits)
	{
		int indexDigit = generateRandomInt(freeDigits.length());
		char digit = freeDigits.charAt(indexDigit);
		freeDigits.deleteCharAt(indexDigit);
		return digit;
	}
}
