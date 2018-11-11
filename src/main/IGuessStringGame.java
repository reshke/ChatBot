package main;

public interface IGuessStringGame extends IGame {
	public Boolean guessAnswer(String query);
	public int postQuery(int leftBound, int rightBound);
	public String getHint(String[] args);
}
