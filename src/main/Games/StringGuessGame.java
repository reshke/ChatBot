package main.Games;


import main.IRandomGenerator;
import main.IResult;
import main.RandomGenerator;
import main.Result;
import main.ResultState;
import main.Commands.Command;
import main.classLoader.IModule;
import main.Game;
import main.GameState;
import main.ICommand;

public class StringGuessGame extends Game implements IModule{
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
	
	public Boolean guessAnswer(String arg) {
		return dataString.equals(arg);
	}
	
	public IResult<String> guessAnswer(String[] args) {
		if (args.length != 2)
			return new Result("Count of arguments is not correct", ResultState.UNSUPPORTED_OPERATION);
		
		return new Result(this.guessAnswer(args[1])
				? "You guessed right!"
				: "You are wrong!");
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
	
	public IResult<String> postQuery(String [] args)
	{
		if (args.length != 3)
			return new Result("Count of arguments is not correct", ResultState.UNSUPPORTED_OPERATION);
		Integer left, right;
		try
		{
			left = Integer.parseInt(args[1]);
			right = Integer.parseInt(args[2]);
		}
		catch (Exception e) 
		{
			return new Result("Arguments should be integer!", ResultState.UNSUPPORTED_OPERATION);
		}
		return new Result(Integer.toString(this.postQuery(left, right)));
	}

	private String getHint(int position){
		if (position < 1 || position > dataString.length())
			return "You can ask digit only in range from 1 to 10";
		return dataString.substring(position - 1, position);
	}
	
	public IResult<String> getHint(String [] args){
		if (args.length != 2)
			return new Result("Count of arguments is not correct", ResultState.UNSUPPORTED_OPERATION);

		Integer questionIndex = Integer.parseInt(args[1]);
		return new Result(getHint(questionIndex));
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
		return new Command[] { new Command("ask", x -> this.postQuery(x)),
				new Command("result", (x) -> this.guessAnswer(x)),
				new Command("hint", (x) -> this.getHint(x))};
	}
	
	@Override
	public IResult<String> getHelp()
	{
		return new Result("To start game type start (length) and bot will make a line of zeros and units this length\r\n" + 
				"To send a request type on the keyboard \"ask leftBorder rightBorder\"\r\n" + 
				"To suggest a string type on the keyboard \"guess ....(your string)\"\r\n" + 
				"To end game type end");
	}
	
	@Override
	public IResult<String> gameName() { return new Result("guessGame"); }
	
	@Override
	public IResult<String> getGameDescriptor() { return new Result("String Guess Game(send 'switch guessGame' to start): \r\n" + 
			"	to start this game type switch guessGame and then start (length)\r\n" + 
			"	The game is as follows:\r\n" + 
			"		the bot makes a line of zeros and units of length (length), \r\n" + 
			"		after which you can ask the bot about the segment \r\n" + 
			"		of this line with indices from a to b,\r\n" + 
			"		in response to which it will return either the number of ones on this segment \r\n" + 
			"		or any number from the segment from 0 to a - b + 1.\r\n" + 
			"		Your task for the minimum number of moves to guess the string."); }
}
