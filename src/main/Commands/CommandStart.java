package main.Commands;
import java.util.function.Consumer;

import main.ICommand;

public class CommandStart<TKey> implements ICommand<TKey> {

	private final Consumer<Integer> function; 
	private final String name;  
	private final TKey key;
	
	public CommandStart(TKey key, String name, Consumer<Integer> function) {
		this.function = function;
		this.name = name;
		this.key = key;
	}
	
	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String executeCommand(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Count of arguments is not correct");
		Integer length;
		try
		{
			length = Integer.parseInt(args[1]);
		}
		catch (Exception e) 
		{
			throw new IllegalArgumentException("Arguments should be integer!");
		}
		function.accept(length);
		return "Game is started!";
	
	}

	@Override
	public TKey getKey() {
		return key;
	}
	
}
