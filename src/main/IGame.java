package main;
/**
 * 
 */

public interface IGame
{
	public IGame startGame();
	public void endGame();
	public void pauseGame();
	public TypeGame getTypeGame();
	public String getHint(int position);
}
