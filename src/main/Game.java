package main;

import main.Commands.Command;

public abstract class Game implements IGame {
	private final ICommandContainer gameCommandContainer = new CommandContainer();
	protected GameState gameState = GameState.NOT_STARTED;
	
	public Game() {
		this.gameCommandContainer.addCommand(new Command("gamehelp", (x) -> this.getHelp()));
		
		for (ICommand<String> command : this.get_commands())
			this.gameCommandContainer.addCommand(command);
	}
	
	public abstract ICommand<String>[] get_commands();
	
	@Override
	public GameState getGameState() { return this.gameState; }
	
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
	public String pauseGame() {
		gameState = GameState.PAUSED;
		
		return "Game paused successfully";
	}
	
	@Override
	public IResult<String> executeQuery(String args[]) {
		return gameCommandContainer.executeCommand(args[0], args);
	}
	
	public IResult<String> getHelp() { return new Result("No help for this game", ResultState.UNSUPPORTED_OPERATION); }
	
	public IResult<String> getGameDescriptor() { return new Result("No game description for this game", ResultState.UNSUPPORTED_OPERATION); }
	
	public IResult<String> gameName() { return new Result(null, ResultState.UNSUPPORTED_OPERATION); }
}
