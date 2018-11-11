package main.Commands;

import java.util.function.Function;

import main.ICommand;

public class CommandHint<TKey> implements ICommand<TKey> {
	private final String name;
	private final TKey key;
	private final Function<String[], String> function;
	
	public CommandHint(TKey key, String name, Function<String[], String> function) {
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
		return function.apply(args);
	}
	
}
