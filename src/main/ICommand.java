package main;

public interface ICommand {
	public String getCommandName();
	public String executeCommand(String[] args);
}
