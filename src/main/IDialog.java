package main;

public interface IDialog {
	public ResultInformation handleQuery(String query);
	public ResultInformation getLastAnswer();
}
