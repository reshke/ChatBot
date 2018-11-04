package main;

import main.Commands.CommandAskStringAndGetString;
import main.Commands.CommandEndGame;
import main.Commands.CommandGuess;
import main.Commands.CommandHint;

public class DialogGame implements IDialogGame {

	private IResult lastAnswer;
	private ICommandContainer<TypeAction> containerGameCommands;
	private ICommandContainer<TypeAction> containerCommonCommands;
	private IGame game;
	private IHelper helper;
	
	public DialogGame(IGame game, ICommandContainer<TypeAction> containerGameCommands, 
			ICommandContainer<TypeAction> containerCommonCommands,
			IHelper helper) {
		this.containerGameCommands = containerGameCommands;
		this.containerCommonCommands = containerCommonCommands;
		this.helper = helper;
		updateContainer(game);
	}
	
	public DialogGame(IGameAskAnswerString game,
			ICommandContainer<TypeAction> containerGameCommands, 
			ICommandContainer<TypeAction> containerCommonCommands,
			IHelper helper) {
		this.containerGameCommands = containerGameCommands;
		this.containerCommonCommands = containerCommonCommands;
		this.helper = helper;
		updateContainer(game);
	}
	
	private void updateContainer(IGameAskAnswerString game) {
		containerGameCommands.clear();
		ICommand gameCommands[] = { new CommandAskStringAndGetString<TypeAction>(TypeAction.ASK, "Ask", (x) -> game.postQuery(x)),
				new CommandGuess<TypeAction>(TypeAction.ANSWER, "ask", (x) -> game.guessAnswer(x)),
				new CommandEndGame<TypeAction>(TypeAction.END, "End", (x) -> game.endGame()),
				new CommandHint<TypeAction>(TypeAction.HINT, "Hint", (x) -> game.getHint(x))};
		for (ICommand<TypeAction> command: gameCommands) {
			containerGameCommands.addCommand(command);
		}
	}
	
	private void updateContainer(IGame game) {
		containerGameCommands.clear();
		ICommand gameCommands[] = { new CommandPostQuery<TypeAction>(TypeAction.ASK, "Ask", (x, y) -> game.postQuery(x, y)),
				new CommandGuess<TypeAction>(TypeAction.ANSWER, "ask", (x) -> game.guessAnswer(x)),
				new CommandEndGame<TypeAction>(TypeAction.END, "End", (x) -> game.endGame()),
				new CommandHint<TypeAction>(TypeAction.HINT, "Hint", (x) -> game.getHint(x))};
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
		try {
			TypeGame gameType = game.getTypeGame();
			String helpMessage = helper.getHelp(gameType);
			return new ResultInformation(helpMessage, ResultState.SUCCESS);
		}
		catch (UnsupportedOperationException | IllegalArgumentException exception) {
			return new ResultInformation(exception.getMessage(), ResultState.WRONG_ARGUMENTS);
		}
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
		return containerGameCommands.executeCommand(TypeAction.START, args);
	}
	
	@Override
	public IResult getHint(String[] args) {
		return containerGameCommands.executeCommand(TypeAction.HINT, args);
	}
}

