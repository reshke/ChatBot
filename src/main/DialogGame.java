package main;
import main.Commands.CommandContainer;
import main.Commands.CommandEndGame;
import main.Commands.CommandGuess;
import main.Commands.CommandHelpGame;
import main.Commands.CommandStart;
import main.Games.StringGuessGame;

public class DialogGame implements IDialog {

	private ResultInformation lastAnswer;
	private final CommandContainer containerGameCommands = new CommandContainer();
	private final CommandContainer containerCommonCommands = new CommandContainer();
	private StringGuessGame game;
	
	@Override
	public ResultInformation handleQuery(String line) {
		lastAnswer = containerCommonCommands.executeQuery(line);
		if (lastAnswer.state == ResultState.UNKNOWN)
			lastAnswer = containerGameCommands.executeQuery(line);
		return lastAnswer;
	}

	@Override
	public ResultInformation getLastAnswer() {
		return lastAnswer;
	}

	public DialogGame() {
		ICommand commonCommands[] = {new CommandHelpGame("gamehelp", (x) -> (new StringGuessGame(1).getHelp())),
				new CommandStart("start", x -> updateGame(x))};
		
		containerCommonCommands.addSetOfCommands(commonCommands);
	}
	
	private void updateContainer() {
		containerGameCommands.clear();
		ICommand gameCommands[] = { new CommandPostQuery("ask", (x, y) -> game.postQuery(x, y)),
				new CommandGuess("guess", (x) -> game.guessAnswer(x)),
				new CommandEndGame("end", (x) -> game.endGame()),
				new CommandHelpGame("gamehelp", (x) -> game.getHelp())};
		
	 	
		containerGameCommands.addSetOfCommands(gameCommands);
	}
	
	private void updateGame(int length) {
		game = new StringGuessGame(length);
		updateContainer();
	}
}

