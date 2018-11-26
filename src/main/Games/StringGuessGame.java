package main.Games;


import main.IRandomGenerator;
import main.RandomGenerator;
import main.Segment;
import main.classLoader.IModule;
import main.Command;
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
	
	public String guessAnswer(String[] args) {
		if (args.length != 2)
			return "Count of arguments is not correct";
		
		return dataString.equals(args[1]) 
				? "You guessed right!"
				: "You are wrong!";
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
	
	public String postQuery(String [] args)
	{
		if (args.length != 3)
			throw new IllegalArgumentException("Count of arguments is not correct");
		Integer left, right;
		try
		{
			left = Integer.parseInt(args[1]);
			right = Integer.parseInt(args[2]);
		}
		catch (Exception e) 
		{
			throw new IllegalArgumentException("Arguments should be integer!");
		}
		return Integer.toString(this.postQuery(left, right));
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
		return new Command[] { new Command("ask", x -> this.postQuery(x)),
				new Command("result", (x) -> this.guessAnswer(x)),
				new Command("hint", (x) -> this.getHint(x)), new Command("gamehelp", x -> this.getHelp())};
	}
	
	@Override
	public String getHelp()
	{
		return "To start game type start (length) and bot will make a line of zeros and units this length\r\n" + 
				"To send a request type on the keyboard \"ask leftBorder rightBorder\"\r\n" + 
				"To suggest a string type on the keyboard \"guess ....(your string)\"\r\n" + 
				"To end game type end";
	}
	
	@Override
	public String gameName() { return "guessGame"; }
	
	@Override
	public String getGameDescriptor() { return "String Guess Game(send 'switch guessGame' to start): \r\n" + 
			"	to start this game type switch guessGame and then start (length)\r\n" + 
			"	The game is as follows:\r\n" + 
			"		the bot makes a line of zeros and units of length (length), \r\n" + 
			"		after which you can ask the bot about the segment \r\n" + 
			"		of this line with indices from a to b,\r\n" + 
			"		in response to which it will return either the number of ones on this segment \r\n" + 
			"		or any number from the segment from 0 to a - b + 1.\r\n" + 
			"		Your task for the minimum number of moves to guess the string."; }
}
