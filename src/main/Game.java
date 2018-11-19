package main;

public abstract class Game implements IGame {
	protected GameState gameState = GameState.NOT_STARTED;
	
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
}
