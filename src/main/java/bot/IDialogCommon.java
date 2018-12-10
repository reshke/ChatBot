package main.java.bot;

public interface IDialogCommon {
	public IResult<String> handleQuery(String query);
	public IResult<String> getLastAnswer();
	public IResult<String> switchGame(String[] args);
	public String[] getCurrentUserExecutableCommands();
	public Game LoadGame(String name);
}
