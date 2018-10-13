package main;
import main.Commands.CommandContainer;
import main.Commands.CommandEndGame;
import main.Commands.CommandGuess;
import main.Commands.CommandHelpGame;
import main.Commands.CommandStart;
import main.Games.StringGuessGame;

public class DialogGame implements IDialogGame {

	private IResult lastAnswer;
	private final ICommandContainer<TypeAction> containerGameCommands = new CommandContainer<TypeAction>();
	private final ICommandContainer<TypeAction> containerCommonCommands = new CommandContainer<TypeAction>();
	private IGame game;
	
	
	public DialogGame(IGame game) {
		this.game = game;
	}
	
	private void updateContainer() {
		containerGameCommands.clear();
		ICommand gameCommands[] = { new CommandPostQuery<TypeAction>(TypeAction.ASK, "Ask", (x, y) -> game.postQuery(x, y)),
				new CommandGuess<TypeAction>(TypeAction.ANSWER, "Guess", (x) -> game.guessAnswer(x)),
				new CommandEndGame<TypeAction>(TypeAction.END, "End", (x) -> game.endGame())};
		for (ICommand<TypeAction> command: gameCommands) {
			containerCommonCommands.addCommand(command);
		}
	}

	

	@Override
	public IResult addRequest(String[] args) {
		return containerGameCommands.executeCommand(TypeAction.ASK, args);
	}

	@Override
	public IResult getLastAnswer(String[] args) {
		return lastAnswer;
	}

	@Override
	public IResult getHelp(String[] args) {
		return containerCommonCommands.executeCommand(TypeAction.HELP, args);
	}

	@Override
	public IResult getState(String[] args) {
		return containerGameCommands.executeCommand(TypeAction.STATE, args);
	}

	@Override
	public IResult stopGame(String[] args) {
		return containerCommonCommands.executeCommand(TypeAction.END, args);
	}

	@Override
	public IResult sendAnswer(String[] args) {
		return containerGameCommands.executeCommand(TypeAction.ANSWER, args);
	}
}

