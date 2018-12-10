package bot;

import userDialog.GameState;

public interface IGame
{
	public IGame startGame();
	public void endGame();
	public String pauseGame();
	public IResult<String> executeQuery(String[] args);
	public GameState getGameState();
}
