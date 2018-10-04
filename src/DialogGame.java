
public class DialogGame implements IDialog {

	private ResultInformation lastAnswer;
	private CommandContainer containerGameCommands = new CommandContainer();
	private CommandContainer containerCommonCommands = new CommandContainer();
	private Game game;
	
	
	
	@Override
	public ResultInformation HandleQuery(String line) {
		lastAnswer = containerCommonCommands.ExecuteQuery(line);
		if (lastAnswer.State == ResultState.Unknowm)
			lastAnswer = containerGameCommands.ExecuteQuery(line);
		return lastAnswer;
	}

	@Override
	public ResultInformation GetLastAnswer() {
		return lastAnswer;
	}

	public DialogGame() {
		ICommand commonCommands[] = {new CommandHelp("help"), new CommandStart("start", 
				x -> UpdateGame(x))};
		
		containerCommonCommands.AddSetOfCommands(commonCommands);
	}
	
	
	private void UpdateContainer() {
		containerGameCommands.Clear();
		ICommand gameCommands[] = { new CommandPostQuery("ask", (x, y) -> game.PostQuery(x, y)),
				new CommandGuess("guess", (x) -> game.GuessAnswer(x)), new CommandEndGame("end", (x) -> game.EndGame())};
		
	 	
		containerGameCommands.AddSetOfCommands(gameCommands);
	}
	
	private void UpdateGame(int length) {
		game = new Game(length);
		UpdateContainer();
	}
}

