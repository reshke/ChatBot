package userDialog;

public interface ICommand<TKey> {
	public TKey getKey();
	public String getCommandName();
	public String executeCommand(String[] args);
}
