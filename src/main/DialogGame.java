package main;
import main.Commands.CommandContainer;
import main.Commands.CommandEndGame;
import main.Commands.CommandGuess;
import main.Commands.CommandHelpGame;
import main.Commands.CommandStart;
import main.Games.StringGuessGame;




public class DialogGame implements IDialog {

	private ResultInformation lastAnswer;
	private CommandContainer containerGameCommands = new CommandContainer();
	private CommandContainer containerCommonCommands = new CommandContainer();
	private StringGuessGame game;
	
	
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
		ICommand commonCommands[] = {new CommandHelpGame("gamehelp", (x) -> (new StringGuessGame(1).GetHelp())),
				new CommandStart("start", x -> UpdateGame(x))};
		
		containerCommonCommands.AddSetOfCommands(commonCommands);
	}
	
	
	private void UpdateContainer() {
		containerGameCommands.Clear();
		ICommand gameCommands[] = { new CommandPostQuery("ask", (x, y) -> game.PostQuery(x, y)),
				new CommandGuess("guess", (x) -> game.GuessAnswer(x)),
				new CommandEndGame("end", (x) -> game.EndGame()),
				new CommandHelpGame("gamehelp", (x) -> game.GetHelp())};
		
	 	
		containerGameCommands.AddSetOfCommands(gameCommands);
	}
	
	private void UpdateGame(int length) {
		game = new StringGuessGame(length);
		UpdateContainer();
	}
}

