package main;


public interface IGame
{
	public IGame startGame();
	public void endGame();
	public void pauseGame();
	public IResult<String> executeQuery(String[] args);
}
