package main;

public abstract class GameDailog implements IDialogGame {
	protected final ICommandContainer<String> gameCommandContainer = new CommandContainer<String>();
	
	@Override
	public IResult postQuery(String[] args) {
		return gameCommandContainer.executeCommand(args[0], args);
	}
}