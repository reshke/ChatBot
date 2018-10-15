package main.Games;

import main.GameState;
import main.IGame;
import main.IRandomGenerator;
import main.TypeGame;

public class NumGame implements IGame {
	private GameState gameState;// = GameState.NotStarted;
	private final String dataString;
	private final IRandomGenerator generator;
	
	public NumGame(IRandomGenerator generator) {
		
		gameState = GameState.NOT_STARTED;
		this.generator = generator;
		dataString = generator.generateRandomString(4);
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

	@Override
	public Boolean guessAnswer(String query) {
		return dataString.equals(query);
	}

	@Override
	public int postQuery(int leftBound, int rightBound) {
		// TODO Auto-generated method stub
		return 12;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeGame getTypeGame() {
		// TODO Auto-generated method stub
		return TypeGame.NUM_GAME;
	}

}
