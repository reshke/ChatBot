package main;

public interface IDialogCommon {
	public IResult<String> handleQuery(String query);
	public IResult<String> getLastAnswer();
	public void switchGame(String typeGame);
}
