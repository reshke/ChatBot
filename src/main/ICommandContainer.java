package main;

public interface ICommandContainer {
	public void clear();
	public IResult<String> executeCommand(String value, String[] args);
	public void addCommand(ICommand<String> command);
	
}
