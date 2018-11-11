package main.Commands;

import java.util.function.Function;

import main.ICommand;

public class CommandGetString<TKey> implements ICommand<TKey> {
	private final TKey key;
	private final String name;
	private final Function<String, String> function;
	
	public CommandGetString(TKey key, String name, Function<String, String> function) {
		this.key = key;
		this.name = name;
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
		{
			return "Count of arguments is not correct";
		}
		String result = function.apply("");
		return result;
	}
	
}
