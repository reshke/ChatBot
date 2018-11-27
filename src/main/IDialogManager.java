package main;
public interface IDialogManager {
	public void startDialog(Long userId);
	public IResult<String> handleQuery(Long userId, String query);
}
