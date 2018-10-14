package main;
import main.Commands.CommandContainer;
import main.Commands.CommandEndGame;
import main.Commands.CommandGuess;
import main.Commands.CommandHelpGame;
import main.Commands.CommandStart;
import main.Games.StringGuessGame;

public class DialogGame implements IDialogGame {

	private IResult lastAnswer;
	private ICommandContainer<TypeAction> containerGameCommands;
	private ICommandContainer<TypeAction> containerCommonCommands;
	private IGame game;
	
	
	public DialogGame(IGame game, ICommandContainer<TypeAction> containerGameCommands, 
			ICommandContainer<TypeAction> containerCommonCommands) {
		this.game = game;
		this.containerGameCommands = containerGameCommands;
		this.containerCommonCommands = containerCommonCommands;
		updateContainer();
	}
	
	private void updateContainer() {
		containerGameCommands.clear();
		ICommand gameCommands[] = { new CommandPostQuery<TypeAction>(TypeAction.ASK, "Ask", (x, y) -> game.postQuery(x, y)),
				new CommandGuess<TypeAction>(TypeAction.ANSWER, "Guess", (x) -> game.guessAnswer(x)),
				new CommandEndGame<TypeAction>(TypeAction.END, "End", (x) -> game.endGame())};
		for (ICommand<TypeAction> command: gameCommands) {
			containerGameCommands.addCommand(command);
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

	@Override
	public IResult startGame(String[] args) {
		// TODO Auto-generated method stub
		return containerGameCommands.executeCommand(TypeAction.START, args);
	}
}

