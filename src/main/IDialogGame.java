package main;

public interface IDialogGame {
	public ResultInformation handleQuery(String query);
	public ResultInformation getLastAnswer();
	public String getHelp();
	public ResultInformation startGame(String[] args);
	public ResultInformation addRequest(String[] args);
	public ResultInformation sendAnswer();
}
