package main;

public abstract class Game implements IGame {
	private GameState gameState;// = GameState.NotStarted;
	
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
		// TODO Auto-generated method stub

		gameState = GameState.PAUSED;

	}
}
