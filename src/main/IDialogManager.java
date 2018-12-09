package main;
public interface IDialogManager {
	public void startDialog(Long userId, IGameLoaderFactory factory);
	public IResult<String> handleQuery(Long userId, String query);
	public Boolean hasDialogWith(Long userId);
	public String[] getUserExecutableCommands(Long userId);
}