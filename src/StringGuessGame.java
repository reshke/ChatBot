

import main.IRandomGenerator;
import main.IResult;
import main.RandomGenerator;
import main.Segment;
import main.Commands.CommandHint;
import main.classLoader.IModule;
import main.Game;
import main.GameState;
import main.ICommand;
import main.IGuessStringGame;

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
	
	public StringGuessGame()
	{
		this.generator = new RandomGenerator();
		int length = 10;

		dataString = generator.generateRandomString(length);
		dataStringLength = length;
		guessedNumber = new int[length + 1];
		calculateGuessedNumber();
		
		for (ICommand<String> command : this.get_commands())
			this.gameCommandContainer.addCommand(command);
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
	public Boolean guessAnswer(String query) {
		return dataString.equals(query);
	}
	public int postQuery(int leftBound, int rightBound) {
		if (rightBound < leftBound || leftBound < 1 || rightBound > dataString.length())
			throw new IllegalArgumentException("Query borders should satisfy following conditionts:\n"
					+ " 1 <= leftBorber <= rightBorder <= string length ");
		
		if (generator.generateRandomBoolean())
			return guessedNumber[rightBound] - guessedNumber[leftBound - 1];
		else
			return generator.generateRandomInt(rightBound - leftBound + 1);
		
	}
	
	public int postQuery(Segment segment)
	{
		return this.postQuery(segment.left, segment.right);
	}

	private String getHint(int position){
		if (position < 1 || position > dataString.length())
			return "You can ask digit only in range from 1 to 10";
		return dataString.substring(position - 1, position);
	}
	
	public String getHint(String [] args){
		if (args.length != 2)
			return "Incorrect count of arguments";

		Integer questionIndex = Integer.parseInt(args[1]);
		return getHint(questionIndex);
	}

	@Override
	public void load() {
		System.out.println("hello from StrignGuessGame!!");
	}

	@Override
	public int run() {
		return 0;
	}

	@Override
	public void unload() {}
	
	public ICommand<String>[] get_commands() {
		return new ICommand[] { new CommandPostQuery<String>("ask", "ask", x -> this.postQuery(x)),
				new CommandGuess<String>("result", "result", (x) -> this.guessAnswer(x)),
				new CommandHint<String>("hint", "hint", (x) -> this.getHint(x))};
	}
}
