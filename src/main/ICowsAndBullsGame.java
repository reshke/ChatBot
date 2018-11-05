package main;

public interface ICowsAndBullsGame extends IGame {
	public Boolean guessAnswer(String query);
	public int postQuery(int leftBound, int rightBound);
}
