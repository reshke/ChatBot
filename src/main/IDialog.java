package main;

public interface IDialog {
	public ResultInformation HandleQuery(String line);
	public ResultInformation GetLastAnswer();
}
