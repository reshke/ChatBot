package main;
public interface IDialogManager {
	public void StartDialog(int userId);
	public IResult handleQuery(int userId, String line);
}
