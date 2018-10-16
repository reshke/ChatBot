package main;
/**
 * 
 */

public interface IGame {
	public IGame startGame();
	public void endGame();
	public void pauseGame();
	public Boolean guessAnswer(String query);
	public int postQuery(int leftBound, int rightBound);
	public TypeGame getTypeGame();
	}
