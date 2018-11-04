package main.Games;

import java.util.Arrays;

import main.GameState;
import main.IGame;
import main.IGameAskAnswerString;
import main.IRandomGenerator;
import main.TypeGame;

public class NumGame implements IGameAskAnswerString {
	private GameState gameState;// = GameState.NotStarted;
	private final String dataString;
	private final IRandomGenerator generator;
	
	public NumGame(IRandomGenerator generator) {
		gameState = GameState.NOT_STARTED;
		this.generator = generator;
		dataString = generator.generateRandomStringInt(4, true);
		if (!isCorrectQuery(dataString))
			throw new IllegalArgumentException("Conveived string should be 4-digits string with different digits");
	}
	
	@Override
	public IGameAskAnswerString startGame() {
		gameState = GameState.RUNNING;
		return null;
	}

	@Override
	public void endGame() {
		if (gameState == GameState.OVER)
			throw new UnsupportedOperationException("Game was already ended!");
		gameState = GameState.OVER;
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
		Integer result = 0;
		for (Integer index = 0; index < lengthCount; index ++) {
			if (first.charAt(index) == second.charAt(index))
				result++;
		}
		return result;
	}
	
	private Integer countUnorderedEqualsSymbols(String first, String second) {
		Integer result = 0;
		for (Integer index = 0; index < first.length(); index++) {
			if (second.contains(Character.toString(first.charAt(index))))
				result++;
		}
		return result;
	}

	@Override
	public void pauseGame() {
		gameState = GameState.PAUSED;
	}

	@Override
	public Boolean guessAnswer(String query) {
		return dataString.equals(query);
	}

	@Override
	public TypeGame getTypeGame() {
		return TypeGame.NUM_GAME;
	}

	@Override
	public String postQuery(String answer) {
		if (!isCorrectQuery(answer))
			throw new IllegalArgumentException("Query should be 4-digits string with different digits!");
		Integer orderedDigits = countOrderedEqualsSymbols(dataString, answer);
		Integer unorderedDigits = countUnorderedEqualsSymbols(dataString, answer);
		return Integer.toString(orderedDigits) + " cows and " + Integer.toString(unorderedDigits - orderedDigits) + " bulls!";
	}
	
	@Override
	public String getHint(int position){
		if (position < 1 || position > 4)
			throw new IllegalArgumentException("You can ask digit only in range from 1 to 4");
		return dataString.substring(position - 1, position);
	}

}
