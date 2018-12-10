package bot;

import java.io.Serializable;

import Commands.Command;
import userDialog.GameState;
import userDialog.ICommand;
import userDialog.ICommandContainer;
import userDialog.Result;

public abstract class Game implements IGame, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7779799682263136873L;
	private ICommandContainer gameCommandContainer;
	protected GameState gameState = GameState.NOT_STARTED;
	
	public Game(ICommandContainer container) {
		this.gameCommandContainer = container;
		this.load();
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
	
	public void save(){
		this.gameCommandContainer.clear();
	}
	
	public void load(){
		gameCommandContainer.addCommand(new Command("gamehelp", (x) -> this.getHelp()));
		
		for (ICommand<String> command : get_commands())
			this.gameCommandContainer.addCommand(command);
	}
	
	@Override
	public IResult<String> executeQuery(String args[]) {
		return gameCommandContainer.executeCommand(args[0], args);
	}
	
	public IResult<String> getHelp() { return new Result("No help for this game", ResultState.UNSUPPORTED_OPERATION); }
	
	public IResult<String> getGameDescriptor() { return new Result("No game description for this game", ResultState.UNSUPPORTED_OPERATION); }
	
	public IResult<String> gameName() { return new Result(null, ResultState.UNSUPPORTED_OPERATION); }
}
