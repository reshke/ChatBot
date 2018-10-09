package main;
public interface IDialogManager {
	public void StartDialog(int userId);
	public ResultInformation handleQuery(int userId, String line);
}
