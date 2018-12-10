package Games;

import java.util.Arrays;

import Commands.Command;
import bot.Game;
import bot.ICommand;
import bot.ICommandContainer;
import bot.IRandomGenerator;
import bot.IResult;
import bot.Result;
import bot.ResultState;

public class NumGame extends Game{
	private static final long serialVersionUID = 5533026093947690885L;
	private final String dataString;
	
	public NumGame(IRandomGenerator generator, ICommandContainer container) {
		super(container);

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
	
	public Boolean guessAnswer(String query)
	{
		return query.equals(this.dataString);
	}

	public IResult<String> guessAnswer(String[] args) {
		if (args.length != 2)
			return new Result("Count of arguments is not correct", ResultState.WRONG_ARGUMENTS);
		if (this.guessAnswer(args[1])) {
			this.endGame();
			return new Result("You won!");
		}
		return new Result(this.postQuery(args[1]));
	}

	public String postQuery(String answer) {
		if (!isCorrectQuery(answer))
			throw new IllegalArgumentException("Query should be 4-digits string with different digits!");
		Integer orderedDigits = countOrderedEqualsSymbols(dataString, answer);
		Integer unorderedDigits = countUnorderedEqualsSymbols(dataString, answer);
		return Integer.toString(orderedDigits) + " cows and " + Integer.toString(unorderedDigits - orderedDigits) + " bulls!";
	}

	public IResult<String> postQuery(String[] args)
	{
		if (args.length != 2)
			return new Result("Count of arguments is not correct", ResultState.WRONG_ARGUMENTS);
		return new Result(this.postQuery(args[1]));
	}
	
	private String getHint(int position){
		if (position < 1 || position > 4)
			return "You can ask digit only in range from 1 to 4";
		return dataString.substring(position - 1, position);
	}
	
	public IResult<String> getHint(String[] args)
	{
		if (args.length != 2)
			return new Result("Count of arguments is not correct", ResultState.WRONG_ARGUMENTS);
		
		try {
			return new Result(this.getHint(Integer.parseInt(args[1])));
		}
		catch (NumberFormatException e) {
			return new Result("second argument must be an integer", ResultState.WRONG_ARGUMENTS);
		}
	}
	
	public ICommand<String>[] get_commands() {
		return new Command[] { new Command("ask", x -> this.postQuery(x)),
				new Command("guess", (x) -> this.guessAnswer(x)),
				new Command("hint", (x) -> this.getHint(x))};
	}
	

	@Override
	public IResult<String> getHelp()
	{
		return new Result("to guess a number type guess \"number\"");
	}
	
	@Override
	public IResult<String> gameName() { return new Result("numGame"); }
	
	@Override
	public IResult<String> getGameDescriptor() { return new Result("num Game (send 'switch numGame' to start):\r\n"
			+ "	The numerical version of the game\r\n"
			+ "	is usually played with 4 digits, but can also be played with 3 or any other number of digits.\r\n" + 
			"\r\n" + 
			"	On a sheet of paper, the players each write a 4-digit secret number. \r\n"
			+ "		The digits must be all different. \r\n"
			+ "		Then, in turn, the players try to guess their opponent's \r\n"
			+ "		number who gives the number of matches. \r\n"
			+ "		If the matching digits are in their right positions, \r\n"
			+ "		they are \"bulls\", if in different positions, they are \"cows\"."); }
}