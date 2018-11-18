package main;

import main.Commands.CommandHelp;
import main.Commands.CommandHelpGame;

public abstract class GameDailog implements IDialogGame {
	protected final ICommandContainer<String> gameCommandContainer = new CommandContainer<String>();

	protected IResult lastAnswer;
	protected IHelper helper;
	protected IGame game;
	
	public GameDailog() {
		this.gameCommandContainer.addCommand(new CommandHelpGame<String>("gamehelp", "gamehelp", (x) -> this.getHelp(x)));
	}
	
	@Override
	public IResult postQuery(String[] args) {
		return gameCommandContainer.executeCommand(args[0], args);
	}
	
	public String getHelp(String args) {
		try {
			TypeGame gameType = game.getTypeGame();
			String helpMessage = helper.getHelp(gameType);
			return helpMessage;
		}
		catch (UnsupportedOperationException | IllegalArgumentException exception) {
			return exception.getMessage();
		}
	}
}