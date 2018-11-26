package main;

import main.Commands.CommandEndGame;

public abstract class Game implements IGame {
	protected final ICommandContainer gameCommandContainer = new CommandContainer();

	protected GameState gameState = GameState.NOT_STARTED;
	
	public Game() {
//		this.gameCommandContainer.addCommand(new CommandHelpGame<String>("gamehelp", "gamehelp", (x) -> this.getHelp(x)));
		this.gameCommandContainer.addCommand(new CommandEndGame<String>("end", "end", (x) -> this.endGame()));
	}
	
	@Override
	public IGame startGame() {
		gameState = GameState.RUNNING;
		return null;
	}
	
	@Override
	public void endGame() {
		if (gameState == GameState.OVER)
			throw new UnsupportedOperationException("Game was already ended!");
		gameState = GameState.OVER;
	}

	@Override
	public void pauseGame() {
		gameState = GameState.PAUSED;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IResult<String> executeQuery(String args[]) {
		return gameCommandContainer.executeCommand(args[0], args);
	}
	
	protected void createBaseForGame(IHelper helper, IGame game, ICommand<String> gameCommands[]) {
		for (ICommand<String> command: gameCommands) {
			this.gameCommandContainer.addCommand(command);
		}
	}
	
	public String GetHelp() {return "No help for this game";}
}
