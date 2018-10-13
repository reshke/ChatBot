package main;

public interface IDialogCommon {
	public IResult handleQuery(String query);
	public IResult getLastAnswer();
	public void switchGame(TypeGame typeGame);
}
