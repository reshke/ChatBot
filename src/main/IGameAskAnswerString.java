package main;

public interface IGameAskAnswerString extends IGame{
	public Boolean guessAnswer(String answer);
	public String postQuery(String answer);
}
