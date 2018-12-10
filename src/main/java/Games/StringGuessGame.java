package Games;

import Commands.Command;
import bot.Game;
import bot.GameState;
import bot.ICommand;
import bot.ICommandContainer;
import bot.IRandomGenerator;
import bot.IResult;
import bot.Result;
import bot.ResultState;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StringGuessGame extends Game{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9082275439919541996L;
	private String dataString;
	private int dataStringLength;
	private int guessedNumber[];	
	private IRandomGenerator generator;
	
	private void calculateGuessedNumber() {
		for (int i = 0; i < dataStringLength; i++){
			guessedNumber[i + 1] = guessedNumber[i] + (dataString.charAt(i) == '1' ? 1 : 0);
		}
	}
	
	private void raiseIfLengthIsIncorrect(int length)
	{
		if (length <= 0)
			throw new IllegalArgumentException("Length of line should be positive number!");
		if (length > 1000)
			throw new IllegalArgumentException("Length of line is too big!");
	}

	public StringGuessGame(int length, IRandomGenerator generator, ICommandContainer container) {
		super(container);
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
		if (args[1].equals("all"))
			return new Result(this.dataString);
		Integer questionIndex = Integer.parseInt(args[1]);
		return new Result(getHint(questionIndex));
	}
	
	@JsonIgnore
	public ICommand<String>[] get_commands() {
		return new Command[] { new Command("ask", x -> this.postQuery(x)),
				new Command("result", (x) -> this.guessAnswer(x)),
				new Command("hint", (x) -> this.getHint(x))};
	}
	
	@JsonIgnore
	@Override
	public IResult<String> getHelp()
	{
		return new Result("To start game type start (length) and bot will make a line of zeros and units this length\r\n" + 
				"To send a request type on the keyboard \"ask leftBorder rightBorder\"\r\n" + 
				"To suggest a string type on the keyboard \"guess ....(your string)\"\r\n" + 
				"To end game type end");
	}
	
	@JsonIgnore
	@Override
	public IResult<String> gameName() { return new Result("guessGame"); }
	
	@JsonIgnore
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