package main;

public interface IAskAnswerStringGame extends IGame{
	public Boolean guessAnswer(String answer);
	public String postQuery(String answer);
	public String getHint(int position);
}
