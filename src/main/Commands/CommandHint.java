package main.Commands;

import java.util.function.Function;

import main.ICommand;

public class CommandHint<TKey> implements ICommand<TKey> {
	private final String name;
	private final TKey key;
	private final Function<Integer, String> function;
	
	public CommandHint(TKey key, String name, Function<Integer, String> function) {
		this.name = name;
		this.key = key;
		this.function = function;
	}
	
	@Override
	public TKey getKey() {
		return key;
	}

	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String executeCommand(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Incorrect count of arguments");
		if (args.length == 1)
		{
			return function.apply(0);
		}
		Integer questionIndex = Integer.parseInt(args[1]);
		return function.apply(questionIndex);
		
	}
	
}
