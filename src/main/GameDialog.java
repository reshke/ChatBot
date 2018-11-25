package main;

import main.Commands.CommandEndGame;
import main.Commands.CommandHelpGame;

public abstract class GameDialog implements IDialogGame {
	protected final ICommandContainer gameCommandContainer = new CommandContainer();

//	protected IResult lastAnswer;
	protected IHelper helper;
	protected IGame game;
	
	public GameDialog() {
		this.gameCommandContainer.addCommand(new CommandHelpGame<String>("gamehelp", "gamehelp", (x) -> this.getHelp(x)));
		this.gameCommandContainer.addCommand(new CommandEndGame<String>("end", "end", (x) -> game.endGame()));
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
	
	public IResult<String> getHelp(String args) {
		return new Result("no help", ResultState.UNSUPPORTED_OPERATION);
//		try {
//			TypeGame gameType = game.getTypeGame();
//			String helpMessage = helper.getHelp(gameType);
//			return new Result(helpMessage, ResultState.SUCCESS);
//		}
//		catch (UnsupportedOperationException | IllegalArgumentException exception) {
//			return new Result(exception.getMessage(), ResultState.UNSUPPORTED_OPERATION);
//		}
	}
}