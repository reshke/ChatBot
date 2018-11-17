package main;

public interface IAskAnswerStringGame{
	public Boolean guessAnswer(String answer);
	public String postQuery(String answer);
	public String getHint(String[] args);
}
