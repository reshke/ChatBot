package main;

public interface ICommandContainer<TValue> {
	public void clear();
	public IResult executeCommand(TValue value, String[] args);
	public void addCommand(ICommand<TValue> command);
	
}
