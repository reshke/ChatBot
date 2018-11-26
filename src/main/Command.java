package main;

import java.util.function.Function;

public final class Command implements ICommand<String> {
	protected final String key;
	protected Function<String[], String> function;
	
	public Command(String key, Function<String[], String> function)
	{
		this.key = key;
		this.function = function;
	}
	
	@Override
	public String getKey() {return key;}

	@Override
	public java.lang.String getCommandName() {return key;}

	@Override
	public java.lang.String executeCommand(java.lang.String[] args)  {
		return this.function.apply(args);
	}
}
