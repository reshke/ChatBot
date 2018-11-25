package main.Games;

import java.util.Arrays;

import main.Game;
import main.IAskAnswerStringGame;
import main.IRandomGenerator;

public class NumGame extends Game implements IAskAnswerStringGame{
	private final String dataString;
	
	public NumGame(IRandomGenerator generator) {
		dataString = generator.generateRandomInteger(4, true);
		if (!isCorrectQuery(dataString))
			throw new IllegalArgumentException("Conveived string should be 4-digits string with different digits");
	}
	
	private Boolean isCorrectQuery(String query) {
		if (query.length() != 4)
			return false;
		char[] symbolArray = query.toCharArray();
		Arrays.sort(symbolArray);
		char previousSymbol = '0' - 1;
		for (char symbol: symbolArray) {
			if (symbol < '0' || symbol > '9')
				return false;
			if (symbol == previousSymbol)
				return false;
			previousSymbol = symbol;
		}
		return true;
	}
	
	private Integer countOrderedEqualsSymbols(String first, String second) {
		Integer lengthCount = Math.min(first.length(), second.length());
		Integer equalsDigits = 0;
		for (Integer index = 0; index < lengthCount; index ++) {
			if (first.charAt(index) == second.charAt(index))
				equalsDigits++;
		}
		return equalsDigits;
	}
	
	private Integer countUnorderedEqualsSymbols(String first, String second) {
		Integer equalDigits = 0;
		for (Integer index = 0; index < first.length(); index++) {
			if (second.contains(Character.toString(first.charAt(index))))
				equalDigits++;
		}
		return equalDigits;
	}

	public Boolean guessAnswer(String query) {
		return dataString.equals(query);
	}

	@Override
	public String postQuery(String answer) {
		if (!isCorrectQuery(answer))
			throw new IllegalArgumentException("Query should be 4-digits string with different digits!");
		Integer orderedDigits = countOrderedEqualsSymbols(dataString, answer);
		Integer unorderedDigits = countUnorderedEqualsSymbols(dataString, answer);
		return Integer.toString(orderedDigits) + " cows and " + Integer.toString(unorderedDigits - orderedDigits) + " bulls!";
	}
	
	private String getHint(int position){
		if (position < 1 || position > 4)
			return "You can ask digit only in range from 1 to 4";
		return dataString.substring(position - 1, position);
	}
	
	@Override
	public String getHint(String [] args){
		if (args.length != 2)
			return "Incorrect count of arguments";

		Integer questionIndex = Integer.parseInt(args[1]);
		return getHint(questionIndex);
	}
}
