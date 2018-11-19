package main;

import main.Commands.CommandHelpGame;

public abstract class GameDialog implements IDialogGame {
	protected final ICommandContainer<String> gameCommandContainer = new CommandContainer<String>();

	protected IResult lastAnswer;
	protected IHelper helper;
	protected IGame game;
	
	public GameDialog() {
		this.gameCommandContainer.addCommand(new CommandHelpGame<String>("gamehelp", "gamehelp", (x) -> this.getHelp(x)));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IResult<String> postQuery(String[] args) {
		return gameCommandContainer.executeCommand(args[0], args);
	}
	
	protected void createBaseForGame(IHelper helper, IGame game, ICommand<String> gameCommands[]) {
		this.helper = helper;
		this.game = game;

		for (ICommand<String> command: gameCommands) {
			this.gameCommandContainer.addCommand(command);
		}
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