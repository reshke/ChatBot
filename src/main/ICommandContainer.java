package main;

public interface ICommandContainer {
	public void clear();
	public IResult executeCommand(String value, String[] args);
	public void addCommand(ICommand<String> command);
	
}
