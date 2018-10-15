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
	public String getHelp(); // Вынести это за логику игры в класс, реализующий диалог игры!
	// Интерфейс реализует класс игры, подходящий исключительно под нашу игру и ее возможные вариации.(Нужно сделать более общий интерфейс?)
}
