package main;

public interface IDialogCommon {
	public ResultInformation handleQuery(String query);
	public ResultInformation getLastAnswer();
	public void switchGame(TypeGame typeGame);
}
