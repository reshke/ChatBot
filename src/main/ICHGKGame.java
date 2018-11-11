package main;

public interface ICHGKGame extends IGame {
	public Boolean postQuery(String query);
	public String getHint(String[] args);
	public String getQuestionWording();
	public IResult getNextQuestion();
}
