package main;

public interface IDialogGame {
	public IResult getLastAnswer(String[] args);
	public IResult getHelp(String[] args);
	public IResult getState(String[] args);
	public IResult stopGame(String[] args);
	public IResult addRequest(String[] args);
	public IResult sendAnswer(String[] args);
}
