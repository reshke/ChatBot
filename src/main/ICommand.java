package main;

public interface ICommand {
	public String GetCommandName();
	public String ExecuteCommand(String[] args);
}
