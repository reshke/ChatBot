package main.Games;

import main.Game;
import main.GameState;
import main.IGuessStringGame;
import main.IRandomGenerator;
import main.TypeGame;
import main.classLoader.IModule;

public class StringGuessGame extends Game implements IGuessStringGame, IModule{
	private final String dataString;
	private final int dataStringLength;
	private final int guessedNumber[];
	private final IRandomGenerator generator;
	
	private void calculateGuessedNumber() {
		for (int i = 0; i < dataStringLength; i++){
			guessedNumber[i + 1] = guessedNumber[i] + (dataString.charAt(i) == '1' ? 1 : 0);
		}
	}

	public GameState getGameState() {
		return gameState;
	}
	
	private void raiseIfLengthIsIncorrect(int length)
	{
		if (length <= 0)
			throw new IllegalArgumentException("Length of line should be positive number!");
		if (length > 1000)
			throw new IllegalArgumentException("Length of line is too big!");
	}
	
	public StringGuessGame(int length, IRandomGenerator generator) {
		raiseIfLengthIsIncorrect(length);
		gameState = GameState.NOT_STARTED;
		this.generator = generator;
		dataString = generator.generateRandomString(length);
		dataStringLength = length;
		guessedNumber = new int[length + 1];
		calculateGuessedNumber();
	}

	@Override
	public Boolean guessAnswer(String query) {
		return dataString.equals(query);
	}

	@Override
	public int postQuery(int leftBound, int rightBound) {
		if (rightBound < leftBound || leftBound < 1 || rightBound > dataString.length())
			throw new IllegalArgumentException("Query borders should satisfy following conditionts:\n"
					+ " 1 <= leftBorber <= rightBorder <= string length ");
		
		if (generator.generateRandomBoolean())
			return guessedNumber[rightBound] - guessedNumber[leftBound - 1];
		else
			return generator.generateRandomInt(rightBound - leftBound + 1);
		
	}
	
	@Override
	public TypeGame getTypeGame() {
		return TypeGame.GUESS_STRING;
	}

	private String getHint(int position){
		if (position < 1 || position > dataString.length())
			return "You can ask digit only in range from 1 to 10";
		return dataString.substring(position - 1, position);
	}
	
	@Override
	public String getHint(String [] args){
		if (args.length != 2)
			return "Incorrect count of arguments";

		Integer questionIndex = Integer.parseInt(args[1]);
		return getHint(questionIndex);
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int run() {
		System.out.println("was");
		return 0;
	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub
		
	}
}
