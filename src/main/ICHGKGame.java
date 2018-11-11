package main;

public interface ICHGKGame extends IGame {
	public Boolean guessAnswer(String query);
	public String getHint(String[] args);
	public String postQuery(String query);
	public IResult getNextQuestion();
}
