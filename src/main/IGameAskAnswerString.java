package main;

public interface IGameAskAnswerString {
	public IGameAskAnswerString startGame();
	public void endGame();
	public void pauseGame();
	public Boolean guessAnswer(String answer);
	public String postQuery(String answer);
	public TypeGame getTypeGame();

}
