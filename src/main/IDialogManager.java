package main;
public interface IDialogManager {
	public void startDialog(Long userId);
	public IResult handleQuery(Long userId, String query);
}
